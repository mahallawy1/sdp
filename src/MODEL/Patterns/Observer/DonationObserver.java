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

    @Override
    public void display() {
        if(super.newNotification) {
            System.out.println("\uD83D\uDD14  New Donation \uD83C\uDF89. " + donationAmount + "EG£ from " + donorName + ".");
            super.newNotification = false;
        }
    }
}