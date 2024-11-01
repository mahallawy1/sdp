package library.MODEL.DAO;

import library.MODEL.DBUtil.DBUtil;
import library.MODEL.DTO.DonationRecordPaymentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonationRecordPaymentDAO {

    // Add a new DonationRecordPayment entry
    public static boolean addDonationRecordPayment(DonationRecordPaymentDTO donationRecordPayment) {
        String query = "INSERT INTO donationrecord_payment (id, donation_id, payment_id) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, donationRecordPayment.getId());
            stmt.setInt(2, donationRecordPayment.getDonationId());
            stmt.setInt(3, donationRecordPayment.getPaymentId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve a DonationRecordPayment by ID
    public static DonationRecordPaymentDTO getDonationRecordPaymentById(int id) {
        String query = "SELECT * FROM donationrecord_payment WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int donationId = rs.getInt("donationId");
                int paymentId = rs.getInt("paymentId");

                return new DonationRecordPaymentDTO(id, donationId, paymentId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update an existing DonationRecordPayment
    public static boolean updateDonationRecordPayment(DonationRecordPaymentDTO donationRecordPayment) {
        String query = "UPDATE donationrecord_payment SET donation_id = ?, payment_id = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, donationRecordPayment.getDonationId());
            stmt.setInt(2, donationRecordPayment.getPaymentId());
            stmt.setInt(3, donationRecordPayment.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a DonationRecordPayment by ID
    public static boolean deleteDonationRecordPayment(int id) {
        String query = "DELETE FROM donationrecord_payment WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all DonationRecordPayments
    public static List<DonationRecordPaymentDTO> getAllDonationRecordPayments() {
        String query = "SELECT * FROM donationrecord_payment";
        List<DonationRecordPaymentDTO> donationRecordPayments = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int donationId = rs.getInt("donationId");
                int paymentId = rs.getInt("paymentId");

                DonationRecordPaymentDTO donationRecordPayment = new DonationRecordPaymentDTO(id, donationId, paymentId);
                donationRecordPayments.add(donationRecordPayment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donationRecordPayments;
    }
}
