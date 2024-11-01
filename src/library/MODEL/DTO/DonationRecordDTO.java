/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.MODEL.DTO;

import java.util.Date;

/**
 *
 * @author ISLAMSOFT
 */
public class DonationRecordDTO {
   private int id;
    private int userId;
    private Date donateDate;
    private int donateTypeId;
    private int amount;
    private boolean status;

    public DonationRecordDTO(int id, int userId, Date donateDate, int donateTypeId, int amount, boolean status) {
        this.id = id;
        this.userId = userId;
        this.donateDate = donateDate;
        this.donateTypeId = donateTypeId;
        this.amount = amount;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Date getDonateDate() { return donateDate; }
    public void setDonateDate(Date donateDate) { this.donateDate = donateDate; }

    public int getDonateTypeId() { return donateTypeId; }
    public void setDonateTypeId(int donateTypeId) { this.donateTypeId = donateTypeId; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; } 
}
