/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author hussien
 */


import MODEL.Patterns.Observer.AObserver;
import MODEL.Patterns.Observer.DonationObserver;
import MODEL.Patterns.Observer.EventObserver;
import MODEL.Patterns.Observer.EventObserver4Volunteer;

public class NotificationView {
    public void showNotification(AObserver observer) {
        if (observer.isNewNotification()) {
            if (observer instanceof EventObserver4Volunteer) {
                System.out.println("\uD83D\uDD14  New Event Added! \n Event Name: " +
                        ((EventObserver4Volunteer) observer).getEventName() + "\n Description: " +
                        ((EventObserver4Volunteer) observer).getDescription() + ".\nSee you \uD83D\uDE04.");
            } else if (observer instanceof EventObserver) {
                System.out.println("\uD83D\uDD14  New Event Added! " +
                        ((EventObserver) observer).getEventName() + " at " + ((EventObserver) observer).getEventDate() +
                        " from " + ((EventObserver) observer).getTimeFrom() + " to " + ((EventObserver) observer).getTimeTo() +
                        ". See you \uD83D\uDE04.");
            } else if (observer instanceof DonationObserver) {
                System.out.println("\uD83D\uDD14  New Donation \uD83C\uDF89. " +
                        ((DonationObserver) observer).getDonationAmount() + "EG£ from " +
                        ((DonationObserver) observer).getDonorName() + ".");
            }
            observer.clearNotification();
        }
    }
}
