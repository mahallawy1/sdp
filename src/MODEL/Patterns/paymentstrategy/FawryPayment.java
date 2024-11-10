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
public class FawryPayment implements PaymentStategy {
     @Override
    public void pay(PaymentDTO payment) {
        System.out.println("Pay by Fawry");
       
    }
}
