/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.MODEL.DTO;

/**
 *
 * @author ISLAMSOFT
 */
public class DonationRecordPaymentDTO {
    private int id;
    private int donationId;
    private int paymentId;

    public DonationRecordPaymentDTO(int id, int donationId, int paymentId) {
        this.id = id;
        this.donationId = donationId;
        this.paymentId = paymentId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getDonationId() { return donationId; }
    public void setDonationId(int donationId) { this.donationId = donationId; }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
}
