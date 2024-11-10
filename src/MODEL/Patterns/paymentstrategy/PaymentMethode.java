/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.paymentstrategy;

import MODEL.DTO.Donation.PaymentDTO;

/**
 *
 * @author belal
 */
public class PaymentMethode {
     private PaymentStategy paymentStrategy;
     
     public PaymentMethode(PaymentStategy X) {
        this.paymentStrategy = X;
    }
     public void executePayment(PaymentDTO payment) {
        paymentStrategy.pay(payment);
    }

    
}
