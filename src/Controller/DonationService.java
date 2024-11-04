/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import MODEL.DAO.DonationRecordDAO;
import MODEL.DAO.Patterns.decorator.*;
import MODEL.DTO.DonationRecordDTO;
import MODEL.DTO.DonationRecordTypeDTO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DonationService {

    private DonationRecordDAO donationRecordDAO;

    public DonationService(Connection connection) {
        this.donationRecordDAO = new DonationRecordDAO(connection);
    }

    public void processDonation(int userId, boolean status) {
        // Base donation (e.g., "Support Us Donation")
        IDonation donation = new SupportUsDonation(50.0); // Assume base amount is 50

        // Apply decorators for additional donation types
        donation = new CharityDonation(donation, 30.0);  // Additional 30 for Charity
        donation = new GazaDonation(donation, 20.0);     // Additional 20 for Gaza
        donation = new SudanDonation(donation, 40.0);    // Additional 40 for Sudan

        // Get cumulative amount
        double cumulativeAmount = donation.getAmount();

        // Create DonationRecordDTO
        DonationRecordDTO donationRecord = new DonationRecordDTO();
        donationRecord.setUserId(userId);
        donationRecord.setDonateDate(new Date());
        donationRecord.setCumulativeAmount((int) cumulativeAmount);
        donationRecord.setStatus(status);

        // Create DonationRecordTypeDTO for each type
        List<DonationRecordTypeDTO> donationTypes = new ArrayList<>();
        donationTypes.add(new DonationRecordTypeDTO(0, 0, "Support Us Donation", 50));
        donationTypes.add(new DonationRecordTypeDTO(0, 0, "Charity Donation", 30));
        donationTypes.add(new DonationRecordTypeDTO(0, 0, "Gaza Donation", 20));
        donationTypes.add(new DonationRecordTypeDTO(0, 0, "Sudan Donation", 40));

        // Save to the database
        try {
            donationRecordDAO.createDonationRecord(donationRecord, donationTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
