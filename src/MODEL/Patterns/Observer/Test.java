package MODEL.Patterns.Observer;

public class Test {
    public static void main(String[] args) {
        // subjects
        EventSubject eventSubj = new EventSubject();
        DonationSubject donationSubj = new DonationSubject();
        // observers
        EventObserver eventObsrv = new EventObserver(eventSubj);
        DonationObserver donationObsrv = new DonationObserver(donationSubj);
        // notification
        eventSubj.setNotification("Fall Event", "23/2/2025", "9:00", "13:00");
        donationSubj.setNotification("Mariam", 200.99);
        eventSubj.setNotification("Summer Event", "23/2/2025", "9:00", "13:00");
        donationSubj.setNotification("Mariam", 200000.99);

    }
}