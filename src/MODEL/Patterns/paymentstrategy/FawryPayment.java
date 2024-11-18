/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.paymentstrategy;

import MODEL.DTO.Donation.PaymentDTO;
import View.UserView;

/**
 *
 * @author belal
 */
public class FawryPayment implements PaymentStategy {
    private UserView userView;
    public FawryPayment(UserView userView) {
        this.userView = userView;
    }
     @Override
    public void pay(PaymentDTO payment) {
        userView.showMessage("Pay by Fawry");//
       
    }
}
