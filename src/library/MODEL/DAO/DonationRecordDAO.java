/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.MODEL.DAO;
import library.MODEL.DTO.DonationRecordDTO;
import library.MODEL.DBUtil.DBUtil;

/**
 *
 * @author ISLAMSOFT
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonationRecordDAO {

    public static boolean addDonationRecord(DonationRecordDTO donationRecord) {
        String sql = "INSERT INTO donationrecord (userId, donateDate, donateTypeId, amount, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, donationRecord.getUserId());
            pstmt.setDate(2, new java.sql.Date(donationRecord.getDonateDate().getTime()));
            pstmt.setInt(3, donationRecord.getDonateTypeId());
            pstmt.setInt(4, donationRecord.getAmount());
            pstmt.setBoolean(5, donationRecord.isStatus());
            
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static DonationRecordDTO getDonationRecordById(int id) {
        String sql = "SELECT * FROM donationrecord WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new DonationRecordDTO(
                    rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getDate("donateDate"),
                    rs.getInt("donateTypeId"),
                    rs.getInt("amount"),
                    rs.getBoolean("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<DonationRecordDTO> getAllDonationRecords() {
        List<DonationRecordDTO> donationRecords = new ArrayList<>();
        String sql = "SELECT * FROM donationrecord";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                DonationRecordDTO donationRecord = new DonationRecordDTO(
                    rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getDate("donateDate"),
                    rs.getInt("donateTypeId"),
                    rs.getInt("amount"),
                    rs.getBoolean("status")
                );
                donationRecords.add(donationRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donationRecords;
    }
}
