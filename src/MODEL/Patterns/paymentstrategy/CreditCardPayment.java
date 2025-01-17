/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.paymentstrategy;

import MODEL.DTO.Donation.PaymentDTO;
import View.UserView;
import View.UtilityHandler;
/**
 *
 * @author belal
 */
public class CreditCardPayment implements PaymentStategy {
        private UtilityHandler UI;
    public CreditCardPayment() {
        this.UI  = new UtilityHandler();
    }
    @Override
    public void pay(PaymentDTO payment) {
        this.UI.showMessage("Pay by Credit Card");
        
        
    }
    
}
