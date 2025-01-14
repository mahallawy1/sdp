package MODEL.Patterns.Command.Manager;

import MODEL.DAO.DonationRecordDAO;
import MODEL.DTO.Donation.DonationRecordDTO;
import MODEL.DTO.Donation.DonationRecordTypeDTO;

import java.sql.SQLException;
import java.util.List;

public class DonationManager {

    private DonationRecordDTO donation;
    private DonationRecordDAO dao;
    private List<DonationRecordTypeDTO> donationTypes;
    private int donationId;

    public DonationManager(DonationRecordDTO donation, DonationRecordDAO dao, List<DonationRecordTypeDTO> donationTypes) {
        this.donation = donation;
        this.dao = dao;
        this.donationTypes = donationTypes;
    }

    public DonationManager(DonationRecordDTO donation) {
        this.donation = donation;
    }


    public void setDonation(DonationRecordDTO donation) {
        this.donation = donation;
    }

    public int getDonationId() {
        return donationId;
    }

    public void addDonation() throws SQLException {
        donationId = dao.createDonationRecord(donation, donationTypes);
    }

}
