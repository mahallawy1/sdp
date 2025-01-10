package MODEL.Patterns.Observer;

public class DonationObserver extends AObserver{
    private String donorName;
    private Double donationAmount;

    public DonationObserver(DonationSubject subject) {
        super(subject);
    }

    @Override
    public void update() {
        DonationSubject subj = (DonationSubject)(super.subject);
        this.donorName = subj.donorName;
        this.donationAmount = subj.donationAmount;
        super.newNotification = true;
    }

    public String getDonorName() {
        return donorName;
    }

    public Double getDonationAmount() {
        return donationAmount;
    }
}