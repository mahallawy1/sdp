package MODEL.Patterns.Proxy;

import MODEL.DTO.User.RoleDTO;
import MODEL.DTO.User.UserDTO;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eslam
 */
public class BookProxy implements IBook {
    private RealBook realBook;
    private String title;
    private Integer publishYear;
    private String description;
    private Integer quantity;
    private Integer bookID;
    private final UserDTO currentUser;

    public BookProxy(String title,Integer publishYear, String description, Integer quantity,Integer bookID,  UserDTO currentUser) {
        this.title = title;
        this.publishYear = publishYear;
        this.description = description;
        this.quantity = quantity;
        this.bookID = bookID;
        this.currentUser = currentUser;
    }

    @Override
    public void display() {
        // Lazy loading
        if (realBook == null) {
            try {
                realBook = new RealBook(title, publishYear, description, quantity,bookID);
            } catch (SQLException ex) {
                Logger.getLogger(BookProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Logging
        System.out.println("Access log: User " + currentUser.getFirstname() + " accessed book at " + new Date());
        realBook.display();
    }
    @Override
    public void update(String title, Integer publishYear, String description, Integer quantity) {
        // Access control
        if (!hasEditPermission()) {
            throw new SecurityException("User doesn't have permission to edit books");
        }
        
        // Logging
        System.out.println("Update log: User " + currentUser.getFirstname() + " updated book at " + new Date());
        
        if (realBook != null) {
            realBook.update(title, publishYear, description, quantity);
        }
        
        this.title = title;
        this.publishYear = publishYear;
        this.description = description;
        this.quantity = quantity;
       
    }

    private boolean hasEditPermission() {
        if (currentUser == null) {
            return false;
        }

        RoleDTO userRole = currentUser.getRole();

        if (userRole == null) {
            return false;
        }

        String roleName = userRole.getName().toUpperCase();
        return roleName.equals("ADMIN") || 
               roleName.equals("EDITOR") || 
               roleName.equals("LIBRARIAN");
    }
}