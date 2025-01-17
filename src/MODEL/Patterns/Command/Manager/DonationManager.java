package MODEL.Patterns.Command.Manager;

import MODEL.DAO.DonationRecordDAO;
import MODEL.DTO.Donation.DonationRecordDTO;
import MODEL.DTO.Donation.DonationRecordTypeDTO;

import java.sql.SQLException;
import java.util.List;

public class DonationManager {

    public DonationManager(){

    }

    public int addDonation(DonationRecordDTO donation, List<DonationRecordTypeDTO> donationTypes) throws SQLException {
        DonationRecordDAO dao = new DonationRecordDAO();
        return dao.createDonationRecord(donation, donationTypes);
    }

}
