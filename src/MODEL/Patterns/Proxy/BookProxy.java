package MODEL.Patterns.Proxy;


import MODEL.DTO.User.RoleDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Proxy.IBook;
import MODEL.Patterns.Proxy.RealBook;
import View.UtilityHandler;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Eslam
 */
public class BookProxy implements IBook {
    private RealBook realBook;
    private String title;
    private String author;
    private double price;
    private final UserDTO currentUser; 
    private UtilityHandler UI;
    public BookProxy(String title, String author, double price, UserDTO currentUser) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.currentUser = currentUser;
        this.UI = new UtilityHandler();
    }
    
    @Override
    public void display() {
        // Lazy loading
        if (realBook == null) {
            realBook = new RealBook(title, author, price);
        }
        // Logging
        UI.showMessage("Access log: User " + currentUser.getFirstname() + " accessed book at " + new Date());
        realBook.display();
    }
    
    @Override
    public void update(String title, String author, double price) {
        // Access control
        if (!hasEditPermission()) {
            throw new SecurityException("User doesn't have permission to edit books");
        }
        
        // Logging
        UI.showMessage("Update log: User " + currentUser.getFirstname() + " updated book at " + new Date());
        
        if (realBook != null) {
            realBook.update(title, author, price);
        }
        
        this.title = title;
        this.author = author;
        this.price = price;
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