/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DTO.Donation;

import java.util.Date;

/**
 *
 * @author mahallawy
 */
public class DonationRecordDTO {
    private int id;
    private int userId;
    private Date donateDate;
    private int cumulativeAmount;
    private boolean status;

    public DonationRecordDTO() {}

    public DonationRecordDTO(int id, int userId, Date donateDate, int cumulativeAmount, boolean status) {
        this.id = id;
        this.userId = userId;
        this.donateDate = donateDate;
        this.cumulativeAmount = cumulativeAmount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDonateDate() {
        return donateDate;
    }

    public void setDonateDate(Date donateDate) {
        this.donateDate = donateDate;
    }

    public int getCumulativeAmount() {
        return cumulativeAmount;
    }

    public void setCumulativeAmount(int cumulativeAmount) {
        this.cumulativeAmount = cumulativeAmount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
