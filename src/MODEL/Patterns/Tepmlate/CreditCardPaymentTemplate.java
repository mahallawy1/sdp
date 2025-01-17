/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Tepmlate;

import MODEL.DTO.Donation.PaymentDTO;
import MODEL.Patterns.paymentstrategy.CreditCardPayment;
import MODEL.Patterns.paymentstrategy.PaymentMethode;
import View.UserView;
import java.sql.Connection;
/**
 *
 * @author belal
 */
public class CreditCardPaymentTemplate extends DonationPaymentTemplate {

    private PaymentMethode paymentMethode;

    public CreditCardPaymentTemplate(UserView userView ,Connection connection) {
        super(userView,connection);
        
        this.paymentMethode = new PaymentMethode(new CreditCardPayment());
    }

    @Override
    public void executePayment(PaymentDTO payment) {
       
       
        paymentMethode.executePayment(payment);
    }
}
