package Controller;

import MODEL.DAO.DonationRecordDAO;
import MODEL.Patterns.decorator.*;
import MODEL.Patterns.singleton.DbConnectionSingleton;
import MODEL.DTO.DonationRecordDTO;
import MODEL.DTO.DonationRecordTypeDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DonationService {

    public void processDonation(int userId, boolean status) {
        IDonation donation = new SupportUsDonation(50.0);
        donation = new CharityDonation(donation, 30.0);
        donation = new GazaDonation(donation, 20.0);
        donation = new SudanDonation(donation, 40.0);

        double cumulativeAmount = donation.getAmount();

        DonationRecordDTO donationRecord = new DonationRecordDTO();
        donationRecord.setUserId(userId);
        donationRecord.setDonateDate(new Date());
        donationRecord.setCumulativeAmount((int) cumulativeAmount);
        donationRecord.setStatus(status);

        List<DonationRecordTypeDTO> donationTypes = new ArrayList<>();
        donationTypes.add(new DonationRecordTypeDTO(0, 0, "Support Us Donation", 50));
        donationTypes.add(new DonationRecordTypeDTO(0, 0, "Charity Donation", 30));
        donationTypes.add(new DonationRecordTypeDTO(0, 0, "Gaza Donation", 20));
        donationTypes.add(new DonationRecordTypeDTO(0, 0, "Sudan Donation", 40));

        try (Connection connection = DbConnectionSingleton.getInstance().getConnection()) {
            DonationRecordDAO donationRecordDAO = new DonationRecordDAO(connection);
            donationRecordDAO.createDonationRecord(donationRecord, donationTypes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
