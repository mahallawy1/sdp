/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Proxy;

import MODEL.DTO.Donation.DonationRecordDTO;
import MODEL.DTO.User.UserDTO;
import java.util.Date;

/**
 *
 * @author Eslam
 */
public class Test {
       public static void main(String[] args) {
        // Sample Users
        UserDTO admin = new UserDTO(1, "admin123", "admin@mail.com", "Admin User", null, "1234567890", 1, 1);
        UserDTO volunteer = new UserDTO(2, "volunteer123", "volunteer@mail.com", "Volunteer User", null, "0987654321", 3, 1);

        // Sample Donation Records
        DonationRecordDTO donation = new DonationRecordDTO(101, 1, new Date(), 500, true);

        // Proxy Implementation
        DonationService donationProxy = new DonationServiceProxy();

        System.out.println("Admin Access:");
        donationProxy.displayDonationDetails(admin, donation);

        System.out.println("\nVolunteer Access:");
        donationProxy.displayDonationDetails(volunteer, donation);
    }
}
