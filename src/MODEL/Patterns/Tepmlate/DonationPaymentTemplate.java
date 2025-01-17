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
            PaymentConfirmation(payment);
        
    }

    
     protected final void validateDonationRecord(DonationRecordDTO donationRecord) throws Exception {
        String sql = "SELECT id FROM donationrecord WHERE id = ? AND status = 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, donationRecord.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new Exception("Invalid or inactive donation record.");
                }
                else{
                   UI.showMessage("Valid and active donation record");
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
               else{
                   UI.showMessage("Accept Methode for Payment");
               }
                
           }
       
         
    }
    }

    protected final void PaymentConfirmation(PaymentDTO payment) throws Exception {

        String sql = "SELECT id FROM payment WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setInt(1, payment.getId());
           try (ResultSet rs = stmt.executeQuery()) {
               if (!rs.next()) {
                   throw new Exception("Payment Not Confermation .");
               }
               else{
                   UI.showMessage("Payment Confermation");
               }
           }
            

        }
    }

    
    public abstract void executePayment(PaymentDTO payment);
}
