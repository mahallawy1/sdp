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
        display();
    }

    @Override
    public void display() {
        System.out.println("New Donation \uD83C\uDF89. " + donationAmount + "EG£ from " + donorName + ".");
    }
}