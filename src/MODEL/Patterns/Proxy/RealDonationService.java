/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Proxy;

import MODEL.DTO.Donation.DonationRecordDTO;
import MODEL.DTO.User.UserDTO;

/**
 *
 * @author Eslam
 */
public class RealDonationService implements DonationService {

    @Override
    public void displayDonationDetails(UserDTO user, DonationRecordDTO donationRecord) {
        System.out.println("Fetching donation details for User: " + user.getFirstname());
        System.out.println("Donation ID: " + donationRecord.getId());
        System.out.println("Date: " + donationRecord.getDonateDate());
        System.out.println("Amount: " + donationRecord.getCumulativeAmount());
        System.out.println("Status: " + (donationRecord.isStatus() ? "Completed" : "Pending"));
    }
}
