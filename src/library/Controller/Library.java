package library.Controller;


//////////////////
import library.MODEL.DAO.AddressDAO;
import library.MODEL.DAO.DonationRecordDAO;
import library.MODEL.DAO.Patterns.decorator.*;
import library.MODEL.DAO.RoleDAO;
import library.MODEL.DAO.UserDAO;
import library.MODEL.DBUtil.DBUtil;
import library.MODEL.DTO.AddressDTO;
import library.MODEL.DTO.RoleDTO;
import library.MODEL.DTO.UserDTO;
import library.MODEL.DTO.DonationRecordDTO;
import library.MODEL.DTO.DonationRecordTypeDTO;

import java.sql.SQLException;
import java.util.Date;
/////////////////
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Test connection
            Connection conn = DBUtil.getConnection();
            if (conn != null) {
                System.out.println("Connection established successfully.");
            }
            //DBUtil.close(conn, null);

           // Loop to continuously prompt the user for operations
            while (true) {
                System.out.println("\nChoose an operation:");
                System.out.println("1. Add User");
                System.out.println("2. Retrieve User by ID");
                System.out.println("3. Update User");
                System.out.println("4. Retrieve All Users");
                System.out.println("5. Delete User by ID");
              System.out.println("6. Add Donation");
                System.out.println("7. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Add User
                        UserDTO newUser = new UserDTO();
                        System.out.print("Enter password: ");
                        newUser.setPassword(scanner.nextLine());
                        System.out.print("Enter email: ");
                        newUser.setEmail(scanner.nextLine());
                        System.out.print("Enter first name: ");
                        newUser.setFirstname(scanner.nextLine());
                        System.out.print("Enter address ID: ");
                        newUser.setAddressId(scanner.nextInt());
                        System.out.print("Enter mobile phone: ");
                        newUser.setMobilePhone(scanner.next());
                        System.out.print("Enter role ID: ");
                        newUser.setRoleId(scanner.nextInt());
                        System.out.print("Enter status (true/false): ");
                        newUser.setStatus(scanner.nextInt());

                        boolean isAdded = UserDAO.addUser(newUser);
                        System.out.println("User added: " + isAdded);
                        break;

                    case 2:
                        // Retrieve User by ID
                        System.out.print("Enter user ID: ");
                        int userId = scanner.nextInt();
                        UserDTO retrievedUser = UserDAO.getUserById(userId);

                        if (retrievedUser != null) {
                            // Fetch Address and Role
                            AddressDTO address = AddressDAO.getAddressById(retrievedUser.getAddressId());
                            RoleDTO role = RoleDAO.getRoleById(retrievedUser.getRoleId());

                            System.out.println("User ID: " + retrievedUser.getId());
                            System.out.println("Name: " + retrievedUser.getFirstname());
                            System.out.println("Email: " + retrievedUser.getEmail());
                            System.out.println("Mobile Phone: " + retrievedUser.getMobilePhone());
                            System.out.println("Status: " + retrievedUser.getStatus());
                            System.out.println("Role: " + (role != null ? role.getName() : "Role not found"));
                            System.out.println("Address: " + (address != null ? address.getName() : "Address not found"));
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 3:
                        // Update User
                        System.out.print("Enter user ID to update: ");
                        int updateUserId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        UserDTO userToUpdate = UserDAO.getUserById(updateUserId);

                        if (userToUpdate != null) {
                            System.out.print("Enter new first name: ");
                            userToUpdate.setFirstname(scanner.nextLine());
                            System.out.print("Enter new email: ");
                            userToUpdate.setEmail(scanner.nextLine());
                            System.out.print("Enter new mobile phone: ");
                            userToUpdate.setMobilePhone(scanner.nextLine());
                            System.out.print("Enter new status (true/false): ");
                            userToUpdate.setStatus(scanner.nextInt());

                            boolean isUpdated = UserDAO.updateUser(userToUpdate);
                            System.out.println("User updated: " + isUpdated);
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 4:
                        // Retrieve All Users
                        List<UserDTO> users = UserDAO.getAllUsers();
                        System.out.println("All users:");

                        for (UserDTO user : users) {
                            // Fetch Address and Role for each user
                            String userAddress = AddressDAO.getFullAddressPath(user.getAddressId());
                            RoleDTO userRole = RoleDAO.getRoleById(user.getRoleId());

                            System.out.println("User ID: " + user.getId());
                            System.out.println("Name: " + user.getFirstname());
                            System.out.println("Email: " + user.getEmail());
                            System.out.println("Mobile Phone: " + user.getMobilePhone());
                            System.out.println("Status: " + user.getStatus());
                            System.out.println("Role: " + (userRole != null ? userRole.getName() : "Role not found"));
                            System.out.println("Address: " + (userAddress != null ? userAddress : "Address not found"));
                            System.out.println();
                        }
                        break;
case 6:
                        // Add Donation
                        System.out.print("Enter user ID: ");
                         userId = scanner.nextInt();

                        // Start with the base donation
                        IDonation donation = new SupportUsDonation(50.0); // Base donation amount (e.g., Support Us)
                        List<DonationRecordTypeDTO> donationTypes = new ArrayList<>();
                        donationTypes.add(new DonationRecordTypeDTO(0, 0, "Support Us Donation", 50));

                        // Additional donation options
                        System.out.print("Add Charity Donation? (y/n): ");
                        if (scanner.next().equalsIgnoreCase("y")) {
                            System.out.print("Enter amount for Charity Donation: ");
                            double charityAmount = scanner.nextDouble();
                            donation = new CharityDonation(donation, charityAmount);
                            donationTypes.add(new DonationRecordTypeDTO(0, 0, "Charity Donation", (int) charityAmount));
                        }

                        System.out.print("Add Gaza Donation? (y/n): ");
                        if (scanner.next().equalsIgnoreCase("y")) {
                            System.out.print("Enter amount for Gaza Donation: ");
                            double gazaAmount = scanner.nextDouble();
                            donation = new GazaDonation(donation, gazaAmount);
                            donationTypes.add(new DonationRecordTypeDTO(0, 0, "Gaza Donation", (int) gazaAmount));
                        }

                        System.out.print("Add Sudan Donation? (y/n): ");
                        if (scanner.next().equalsIgnoreCase("y")) {
                            System.out.print("Enter amount for Sudan Donation: ");
                            double sudanAmount = scanner.nextDouble();
                            donation = new SudanDonation(donation, sudanAmount);
                            donationTypes.add(new DonationRecordTypeDTO(0, 0, "Sudan Donation", (int) sudanAmount));
                        }

                        // Get the cumulative amount from the decorated donation object
                        double cumulativeAmount = donation.getAmount();

                        // Create DonationRecordDTO
                        DonationRecordDTO donationRecord = new DonationRecordDTO();
                        donationRecord.setUserId(userId);
                        donationRecord.setDonateDate(new Date());
                        donationRecord.setCumulativeAmount((int) cumulativeAmount);
                        donationRecord.setStatus(true);

                        // Save to database
                        DonationRecordDAO donationRecordDAO = new DonationRecordDAO(conn); // Use the existing connection
                        try {
                            donationRecordDAO.createDonationRecord(donationRecord, donationTypes);
                            System.out.println("Donation successfully added with cumulative amount: " + cumulativeAmount);
                        } catch (SQLException e) {
                            System.out.println("Error saving donation: " + e.getMessage());
                        }
                        break;


                    case 7:
                        System.out.println("Exiting...");
                        DBUtil.close(conn, null);
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        
        
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
