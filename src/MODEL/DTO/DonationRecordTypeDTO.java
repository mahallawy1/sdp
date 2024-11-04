/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DTO;

/**
 *
 * @author mahallawy
 */
public class DonationRecordTypeDTO {
    private int id;
    private int donationRecordId;
    private String donationTypeName;
    private int amount;

    public DonationRecordTypeDTO() {}

    public DonationRecordTypeDTO(int id, int donationRecordId, String donationTypeName, int amount) {
        this.id = id;
        this.donationRecordId = donationRecordId;
        this.donationTypeName = donationTypeName;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDonationRecordId() {
        return donationRecordId;
    }

    public void setDonationRecordId(int donationRecordId) {
        this.donationRecordId = donationRecordId;
    }

    public String getDonationTypeName() {
        return donationTypeName;
    }

    public void setDonationTypeName(String donationTypeName) {
        this.donationTypeName = donationTypeName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

