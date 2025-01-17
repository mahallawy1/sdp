package MODEL.Patterns.Proxy;

import MODEL.DTO.User.RoleDTO;
import MODEL.DTO.User.UserDTO;

public class ProxyPatternTest {
    private UserDTO adminUser;
    private UserDTO normalUser;
    private IBook bookForAdmin;
    private IBook bookForUser;

    public void setUp() {
        // Create roles
        RoleDTO adminRole = new RoleDTO(1, "ADMIN");
        RoleDTO userRole = new RoleDTO(2, "USER");

        // Create users with different roles
        adminUser = new UserDTO(15, "admin123", "admin@mail.com", "Admin User", null, null,null,null,null);
        adminUser.setRole(adminRole);

        normalUser = new UserDTO(16, "user123", "user@mail.com", "Normal User", null, null,null,null,null);
        normalUser.setRole(userRole);

        // Create books using proxy for both users with updated parameters
        bookForAdmin = new BookProxy("Design Patterns", 1994, "A book on design patterns by Gang of Four", 100, 2, adminUser);
        bookForUser = new BookProxy("Java Programming", 1995, "A comprehensive guide by James Gosling", 50, 3, normalUser);
    }
    //j

    public void testAdminAccess() {
        System.out.println("Testing Admin Access...");
        bookForAdmin.display();

        try {
            bookForAdmin.update("Design Patterns Updated", 1999, "Updated version of the book", 120);
            System.out.println("Admin update permission: PASSED");
        } catch (SecurityException e) {
            System.out.println("Admin update permission: FAILED");
        }
    }

    public void testNormalUserAccess() {
        System.out.println("Testing Normal User Access...");
        bookForUser.display();

        try {
            bookForUser.update("Attempted Update", 2000, "New Author", 60);
            System.out.println("Normal user update permission: FAILED");
        } catch (SecurityException e) {
            System.out.println("Normal user update permission: PASSED");
        }
    }

    public void testLazyLoading() {
        System.out.println("Testing Lazy Loading...");
        IBook lazyBook = new BookProxy("Lazy Book", 2023, "A lazy loading book", 10, 3, adminUser);

        try {
            lazyBook.display();
            System.out.println("Lazy loading: PASSED");
        } catch (Exception e) {
            System.out.println("Lazy loading: FAILED");
        }
    }



    public static void main(String[] args) {
        ProxyPatternTest test = new ProxyPatternTest();
        test.setUp();

        test.testAdminAccess();
        test.testNormalUserAccess();
        test.testLazyLoading();
      
    }
}