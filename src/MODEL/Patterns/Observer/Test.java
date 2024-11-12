package MODEL.Patterns.Observer;

import java.time.LocalDate;
import java.time.LocalTime;

public class Test {
    public static void main(String[] args) {
        // subjects
        EventSubject eventSubj = new EventSubject();
        DonationSubject donationSubj = new DonationSubject();
        // observers
        EventObserver eventObsrv = new EventObserver(eventSubj);
        DonationObserver donationObsrv = new DonationObserver(donationSubj);
        // notification
        eventSubj.setNotification("Fall Event", LocalDate.of(2025,2,23), LocalTime.of(9,0), LocalTime.of(13,0));
        donationSubj.setNotification("Mariam", 200.99);
        eventSubj.setNotification("Summer Event", LocalDate.of(2025,2,23), LocalTime.of(9,0), LocalTime.of(13,0));
        donationSubj.setNotification("Mariam", 200000.99);

    }
}