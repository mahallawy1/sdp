package test.generalTest.CRUD;

import MODEL.DAO.UserDAO;
import MODEL.DTO.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    private UserDTO testUser;

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialize a test user before each test
        testUser = new UserDTO();
        testUser.setPassword("mohad1212");
        testUser.setEmail("mohamed@example.com");
        testUser.setFirstname("mohamed");
        testUser.setAddressId(1);  // Assuming 1 is a valid address_id in the test database
        testUser.setMobilePhone("23424202334");
        testUser.setRoleId(1);     // Assuming 1 is a valid role_id in the test database
        testUser.setStatus(1);     // Assuming 1 is active status

        // Add the test user to the database and retrieve their ID
        boolean isAdded = UserDAO.addUser(testUser);
        assertTrue(isAdded, "User should be added successfully.");

        // Retrieve the added user to get the generated ID
        UserDTO addedUser = UserDAO.getUserById(testUser.getId());
        assertNotNull(addedUser, "User should exist in the database.");
        testUser.setId(addedUser.getId());  // Update testUser with the generated ID
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Clean up the test user from the database after each test
        UserDAO.deleteUser(testUser.getId());
    }

    @Test
    public void testAddUser() throws SQLException {
        // Verify the user exists in the database after being added in setUp()
        UserDTO retrievedUser = UserDAO.getUserById(testUser.getId());
        assertNotNull(retrievedUser, "User should exist in the database.");
        assertEquals(testUser.getEmail(), retrievedUser.getEmail(), "Emails should match.");
    }

    @Test
    public void testUpdateUser() throws SQLException {
        // Update the test user's email
        testUser.setEmail("updateduser@example.com");
        boolean isUpdated = UserDAO.updateUser(testUser);
        assertTrue(isUpdated, "User should be updated successfully.");

        // Retrieve the user to verify the update
        UserDTO updatedUser = UserDAO.getUserById(testUser.getId());
        assertNotNull(updatedUser, "User should still exist in the database.");
        assertEquals("updateduser@example.com", updatedUser.getEmail(), "Email should be updated.");
    }

    @Test
    public void testDeleteUser() throws SQLException {
        // Delete the user and verify deletion
        boolean isDeleted = UserDAO.deleteUser(testUser.getId());
        assertTrue(isDeleted, "User should be deleted successfully.");

        // Verify the user no longer exists
        UserDTO deletedUser = UserDAO.getUserById(testUser.getId());
        assertNull(deletedUser, "Deleted user should not exist.");
    }

    @Test
    public void testGetUserById() throws SQLException {
        // Retrieve the user by ID and verify correctness
        UserDTO retrievedUser = UserDAO.getUserById(testUser.getId());
        assertNotNull(retrievedUser, "User should be retrievable by ID.");
        assertEquals(testUser.getEmail(), retrievedUser.getEmail(), "Emails should match.");
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        // Retrieve all users and ensure our test user is in the list
        ArrayList<UserDTO> users = UserDAO.getAllUsers();
        assertFalse(users.isEmpty(), "User list should not be empty.");

        // Verify the test user is in the list
        boolean found = users.stream().anyMatch(user -> user.getId() == testUser.getId());
        assertTrue(found, "Test user should be in the list of all users.");
    }
}
