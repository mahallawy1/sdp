/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Tepmlate;

import MODEL.DTO.Donation.PaymentDTO;
import MODEL.Patterns.paymentstrategy.FawryPayment;
import MODEL.Patterns.paymentstrategy.PaymentMethode;
import View.UserView;
import java.sql.Connection;

/**
 *
 * @author belal
 */
public class FawryPaymentTemplate extends DonationPaymentTemplate {

    private PaymentMethode paymentMethode;

     public FawryPaymentTemplate(UserView userView, Connection connection) {
        // Pass the connection to the parent class constructor
        super(userView, connection);
        // Create a PaymentMethode with the specific strategy for Fawry
        this.paymentMethode = new PaymentMethode(new FawryPayment());
    }

    @Override
    public void executePayment(PaymentDTO payment) {
        
    
        paymentMethode.executePayment(payment);
    }
}
