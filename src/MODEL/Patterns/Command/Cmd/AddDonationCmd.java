package MODEL.Patterns.Command.Cmd;

        import MODEL.Patterns.Command.ICommand;
        import MODEL.Patterns.Command.Manager.DonationManager;
        import MODEL.Patterns.Command.Manager.UserManager;

        import java.sql.SQLException;

public class AddDonationCmd implements ICommand {
    public DonationManager donationManager;

    public AddDonationCmd(DonationManager donationManager) {
        this.donationManager = donationManager;
    }

    @Override
    public void execute() throws SQLException {
        donationManager.addDonation();
    }
}


