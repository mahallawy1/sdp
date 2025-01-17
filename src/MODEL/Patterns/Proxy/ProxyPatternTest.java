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
        adminUser = new UserDTO(1, "admin123", "admin@mail.com", "Admin User", null, "1234567890", 1, 1);
        adminUser.setRole(adminRole);

        normalUser = new UserDTO(2, "user123", "user@mail.com", "Normal User", null, "9876543210", 2, 1);
        normalUser.setRole(userRole);

        // Create books using proxy for both users
        bookForAdmin = new BookProxy("Design Patterns", "Gang of Four", 49.99, adminUser);
        bookForUser = new BookProxy("Java Programming", "James Gosling", 39.99, normalUser);
    }

    public void testAdminAccess() {
        System.out.println("Testing Admin Access...");
        bookForAdmin.display();

        try {
            bookForAdmin.update("Design Patterns Updated", "Gang of Four", 59.99);
            System.out.println("Admin update permission: PASSED");
        } catch (SecurityException e) {
            System.out.println("Admin update permission: FAILED");
        }
    }

    public void testNormalUserAccess() {
        System.out.println("Testing Normal User Access...");
        bookForUser.display();

        try {
            bookForUser.update("Attempted Update", "New Author", 29.99);
            System.out.println("Normal user update permission: FAILED");
        } catch (SecurityException e) {
            System.out.println("Normal user update permission: PASSED");
        }
    }

    public void testLazyLoading() {
        System.out.println("Testing Lazy Loading...");
        IBook lazyBook = new BookProxy("Lazy Book", "Author", 29.99, adminUser);

        try {
            lazyBook.display();
            System.out.println("Lazy loading: PASSED");
        } catch (Exception e) {
            System.out.println("Lazy loading: FAILED");
        }
    }

    public void testNullUser() {
        System.out.println("Testing Null User...");
        IBook bookForNullUser = new BookProxy("Test Book", "Test Author", 19.99, null);

        try {
            bookForNullUser.update("New Title", "New Author", 29.99);
            System.out.println("Null user access: FAILED");
        } catch (SecurityException e) {
            System.out.println("Null user access: PASSED");
        }
    }

    public static void main(String[] args) {
        ProxyPatternTest test = new ProxyPatternTest();
        test.setUp();

        test.testAdminAccess();
        test.testNormalUserAccess();
        test.testLazyLoading();
        test.testNullUser();
    }
}
