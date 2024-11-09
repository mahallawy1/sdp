package MODEL.Patterns.Observer;

public class DonationSubject extends ASubject{
    protected String donorName;
    protected Double donationAmount;

    public void setNotification(String donorName, Double donationAmount){
        this.donorName = donorName;
        this.donationAmount = donationAmount;

        super.notifyObservers();
    }
}