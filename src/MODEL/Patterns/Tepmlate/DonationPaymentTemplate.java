/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Tepmlate;

import MODEL.DAO.DonationRecordDAO;
import MODEL.DAO.UserDAO;
import MODEL.DTO.Donation.DonationRecordDTO;
import MODEL.DTO.Donation.PaymentDTO;
import MODEL.DTO.User.UserDTO;
import java.sql.SQLException;
import java.sql.Connection;
import MODEL.DAO.PaymentDAO;
import View.UserView;

/**
 *
 * @author belal
 */
public abstract class DonationPaymentTemplate {
    private UserView userView;
    protected Connection connection;  
    public DonationPaymentTemplate(UserView userView, Connection connection) {
        this.userView = userView;
        this.connection = connection;
    }
     public final void processPayment(DonationRecordDTO donationRecord, PaymentDTO payment) throws SQLException {
        validateDonationRecord(donationRecord);
        validatePayment(payment);
        
        
        //stratgy
        executePayment(payment);
        sendPaymentConfirmation(payment);
    
}
     
    protected final void validateDonationRecord(DonationRecordDTO donationRecord) {
    if (donationRecord.getCumulativeAmount() <= 0) {
            throw new IllegalArgumentException("Donation amount must be greater than zero.");
        }

        
       try {
        
        UserDTO user = UserDAO.getUserById(donationRecord.getUserId());
        
        if (user == null) {
            throw new SQLException("User with ID " + donationRecord.getUserId() + " does not exist.");
        }
    } catch (SQLException e) {
        
        e.printStackTrace();
        throw new RuntimeException("Error during validation", e);
    }
    }
    
    
    
    protected final void validatePayment(PaymentDTO payment) {
        if (payment.getPaymentMethodId() == null || payment.getPaymentMethodId() <= 0) {
            throw new IllegalArgumentException("Invalid payment method ID.");
        }
    }

    

   

    protected final void sendPaymentConfirmation(PaymentDTO payment) throws SQLException {
    PaymentDAO paymentDAO = new PaymentDAO(connection);

   
    paymentDAO.confirmPayment(payment.getId());

    
    userView.showMessage("Payment with ID " + payment.getId() + " has been confirmed successfully.");
}
    // Varying function
    public abstract void executePayment(PaymentDTO payment);
}
