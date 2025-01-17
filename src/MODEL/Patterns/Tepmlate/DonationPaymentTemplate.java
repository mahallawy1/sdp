/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Tepmlate;

import MODEL.DAO.PaymentDAO;
import MODEL.DTO.Donation.DonationRecordDTO;
import MODEL.DTO.Donation.PaymentDTO;
import View.UserView;
import View.UtilityHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class DonationPaymentTemplate {
    private UtilityHandler UI;
    protected final Connection connection;

    public DonationPaymentTemplate(UserView userView, Connection connection) {
        UI = new UtilityHandler();
        this.connection = connection;
    }

    public final void processPayment(DonationRecordDTO donationRecord, PaymentDTO payment) throws Exception {
        
            validateDonationRecord(donationRecord);
            validatePayment(payment);
            executePayment(payment);
            sendPaymentConfirmation(payment);
        
    }

    protected final void validateDonationRecord(DonationRecordDTO donationRecord) throws Exception {
        String sql = "SELECT id FROM donationrecord WHERE id = ? AND status = true";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, donationRecord.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new Exception("Invalid or inactive donation record.");
                }
                else{
                    UI.showMessage("Donation record is valid and active");
            }
        }
        }
    }

    protected final void validatePayment(PaymentDTO payment) throws Exception {
        String sql = "SELECT id FROM paymentmethod WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, payment.getPaymentMethodId());
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new Exception("Invalid payment method.");
                }
            }
        }
    }

    protected final void sendPaymentConfirmation(PaymentDTO payment) throws Exception {
//        String message = "Your payment with ID " + payment.getId() + " has been successfully processed.";
//        String sql = "INSERT INTO payment_confirmation (payment_id, message) VALUES (?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, payment.getId());
//            stmt.setString(2, message);
//            stmt.executeUpdate();
//        }
//        userView.showMessage("Payment confirmation sent successfully.");
    }

    // Abstract method for execution of payment - Implementation will vary
    public abstract void executePayment(PaymentDTO payment);
}
