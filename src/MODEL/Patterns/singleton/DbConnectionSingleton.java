package MODEL.Patterns.singleton;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectionSingleton {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    // Volatile singleton instance
    private static volatile DbConnectionSingleton instance;

    // Private constructor to prevent instantiation
    private DbConnectionSingleton() {
        loadProperties(); // Load properties when instance is created
    }

    // Double-checked locking for thread-safe singleton instance
    public static DbConnectionSingleton getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (DbConnectionSingleton.class) {
                if (instance == null) { // Second check (with locking)
                    instance = new DbConnectionSingleton();
                }
            }
        }
        return instance;
    }

    // Load properties from the src/constants/constants.properties file
    private void loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("constatns/constants.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find constants.properties file");
            }
            properties.load(input);
            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load database properties", e);
        }
    }

    // Method to establish and return a database connection
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to close Connection and PreparedStatement
    public void close(Connection conn, PreparedStatement pstmt) {
        try {
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to close Connection, PreparedStatement, and ResultSet
    public void close(Connection conn, PreparedStatement pstmt, ResultSet rset) {
        try {
            if (rset != null && !rset.isClosed()) {
                rset.close();
            }
            close(conn, pstmt); // Call the other close method to close conn and pstmt
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
