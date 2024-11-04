package MODEL.DBUtil;
/**
 *
 * @author mahallawy
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:8889/liboo"; // Update with your actual database URL
    private static final String USER = "root"; // Update with your actual database username
    private static final String PASSWORD = "root"; // Update with your actual database password

    // Method to establish and return a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to close Connection and PreparedStatement
    public static void close(Connection conn, PreparedStatement pstmt) {
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
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rset) {
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
