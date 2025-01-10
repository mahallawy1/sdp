/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Proxy;

/**
 *
 * @author Eslam
 */
import MODEL.DTO.Donation.DonationRecordDTO;
import MODEL.DTO.User.UserDTO;

public class DonationServiceProxy implements DonationService {

    private final RealDonationService realDonationService;

    public DonationServiceProxy() {
        this.realDonationService = new RealDonationService();
    }

    @Override
    public void displayDonationDetails(UserDTO user, DonationRecordDTO donationRecord) {
        // Allowed roles: 1 (Admin), 2 (Member)
        if (user.getRoleId() != null && (user.getRoleId() == 1 || user.getRoleId() == 2)) {
            System.out.println("Access granted for role: " + getRoleName(user.getRoleId()));
            realDonationService.displayDonationDetails(user, donationRecord);
        } else {
            System.out.println("Access denied for role: " + getRoleName(user.getRoleId()));
        }
    }

    private String getRoleName(Integer roleId) {
        switch (roleId) {
            case 1: return "Admin";
            case 2: return "Member";
            case 3: return "Volunteer";
            default: return "Unknown";
        }
    }
}

