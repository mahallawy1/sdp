/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.MODEL.DAO;
import library.MODEL.DBUtil.DBUtil;
import library.MODEL.DTO.DonationTypeDTO;

/**
 *
 * @author ISLAMSOFT
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonationTypeDAO {

    public static boolean addDonationType(DonationTypeDTO donationType) {
        String sql = "INSERT INTO donation_type (type) VALUES (?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, donationType.getType());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<DonationTypeDTO> getAllDonationTypes() {
        List<DonationTypeDTO> donationTypes = new ArrayList<>();
        String sql = "SELECT * FROM donation_type";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                DonationTypeDTO donationType = new DonationTypeDTO(
                    rs.getInt("id"),
                    rs.getString("name")
                );
                donationTypes.add(donationType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donationTypes;
    }
}
