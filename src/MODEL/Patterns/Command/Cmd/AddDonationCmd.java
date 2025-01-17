package MODEL.Patterns.Command.Cmd;

        import MODEL.DTO.Donation.DonationRecordDTO;
        import MODEL.DTO.Donation.DonationRecordTypeDTO;
        import MODEL.Patterns.Command.ICmd;
        import MODEL.Patterns.Command.ICommand;
        import MODEL.Patterns.Command.Manager.DonationManager;
        import MODEL.Patterns.Command.Manager.UserManager;

        import java.sql.SQLException;
        import java.util.List;

public class AddDonationCmd implements ICmd {
    public DonationManager donationManager;
    public DonationRecordDTO donation;
    public List<DonationRecordTypeDTO> donationTypes;

    public AddDonationCmd(DonationManager donationManager, DonationRecordDTO donation, List<DonationRecordTypeDTO> donationTypes) {
        this.donationManager = donationManager;
        this.donation = donation;
        this.donationTypes = donationTypes;
    }

    @Override
    public Object execute() throws SQLException {
        return donationManager.addDonation(donation, donationTypes);
    }
}


