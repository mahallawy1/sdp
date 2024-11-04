package MODEL.DAO;

import MODEL.DBUtil.DBUtil;
import MODEL.DTO.RoleDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO {

    public static boolean addRole(RoleDTO role) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO Role (type) VALUES (?)");
            pstmt.setString(1, role.getName());

            return pstmt.executeUpdate() == 1;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    public static RoleDTO getRoleById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM Role WHERE id = ?");
            pstmt.setInt(1, id);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                return new RoleDTO(rset.getInt("id"), rset.getString("type"));
            }
        } finally {
            DBUtil.close(conn, pstmt, rset);
        }
        return null;
    }

    public static List<RoleDTO> getAllRoles() throws SQLException {
        List<RoleDTO> roles = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * FROM Role");

            while (rset.next()) {
                roles.add(new RoleDTO(rset.getInt("id"), rset.getString("type")));
            }
        } finally {
            DBUtil.close(conn, (PreparedStatement) stmt, rset);
        }
        return roles;
    }

    public static boolean updateRole(RoleDTO role) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("UPDATE Role SET type = ? WHERE id = ?");
            pstmt.setString(1, role.getName());
            pstmt.setInt(2, role.getId());

            return pstmt.executeUpdate() == 1;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    public static boolean deleteRole(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("DELETE FROM Role WHERE id = ?");
            pstmt.setInt(1, id);

            return pstmt.executeUpdate() == 1;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }
}
