/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MODEL.Patterns.Proxy;

import MODEL.DTO.Donation.DonationRecordDTO;
import MODEL.DTO.User.UserDTO;

/**
 *
 * @author Eslam
 */
public interface DonationService {
    void displayDonationDetails(UserDTO user, DonationRecordDTO donationRecord);
}
