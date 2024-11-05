package Controller;

//////////////////
import MODEL.DAO.AddressDAO;
import MODEL.DAO.DonationRecordDAO;
import MODEL.Patterns.decorator.*;
import MODEL.Patterns.singleton.DbConnectionSingleton;
import MODEL.DAO.RoleDAO;
import MODEL.DAO.UserDAO;
import MODEL.DTO.User.AddressDTO;
import MODEL.DTO.User.RoleDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.DTO.Donation.DonationRecordDTO;
import MODEL.DTO.Donation.DonationRecordTypeDTO;
import MODEL.DTO.Event.EventDTO;
import MODEL.Patterns.factory.AdminEventFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Library {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        try {
            // Obtain singleton connection instance
            conn = DbConnectionSingleton.getInstance().getConnection();
            if (conn != null) {
                System.out.println("Connection established successfully.");
            }
            UserDTO admin = new UserDTO();
            System.out.print("Enter password: ");
                        admin.setPassword(scanner.nextLine());
                        System.out.print("Enter email: ");
                        admin.setEmail(scanner.nextLine());
                        System.out.print("Enter first name: ");
                        admin.setFirstname(scanner.nextLine());
                        System.out.print("Enter address ID: ");
                        admin.setAddressId(scanner.nextInt());
                        System.out.print("Enter mobile phone: ");
                        admin.setMobilePhone(scanner.next());
                        // set admin to id 1
                        admin.setRoleId(1);
                        System.out.print("Enter status (true/false): ");
                        admin.setStatus(scanner.nextInt());

                        boolean isAddedAdmin = UserDAO.addUser(admin);
                        System.out.println("Admin added: " + isAddedAdmin);
            // Loop to continuously prompt the user for operations
            while (true) {
                System.out.println("\nChoose an operation:");
                System.out.println("1. Add User");
                System.out.println("2. Retrieve User by ID");
                System.out.println("3. Update User");
                System.out.println("4. Retrieve All Users");
                System.out.println("5. Delete User by ID");
                System.out.println("6. Add Donation");
                System.out.println("7. Create Event");
                System.out.println("8. Exit");
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

                        // Prompt user for additional donations
                        System.out.print("You have added a 50 Dollar Donation by default. Do you want to add more donations? (y/n): ");
                        if (scanner.next().equalsIgnoreCase("y")) {
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
                        } else {
                            System.out.println("No additional donations added. Processing default 50 Dollar donation...");
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

                       
                        System.out.println("Enter Event ID:");
                        int eventId = Integer.parseInt(scanner.nextLine());

                        System.out.println("Enter Event Name:");
                        String eventName = scanner.nextLine();

                        System.out.println("Enter Event Type ID:");
                        int eventTypeId = Integer.parseInt(scanner.nextLine());

                        System.out.println("Enter Description:");
                        String description = scanner.nextLine();

                        LocalDate eventDate;
                        while (true) {
                            System.out.println("Enter Event Date (yyyy-mm-dd):");
                            try {
                                eventDate = LocalDate.parse(scanner.nextLine());
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid date format. Please use yyyy-mm-dd.");
                            }
                        }

                        LocalTime startTime;
                        while (true) {
                            System.out.println("Enter Start Time (HH:MM):");
                            try {
                                startTime = LocalTime.parse(scanner.nextLine());
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid time format. Please use HH:MM.");
                            }
                        }

                        LocalTime endTime;
                        while (true) {
                            System.out.println("Enter End Time (HH:MM):");
                            try {
                                endTime = LocalTime.parse(scanner.nextLine());
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid time format. Please use HH:MM.");
                            }
                        }
                        System.out.println("Enter Event ID:");
                        int capacity = Integer.parseInt(scanner.nextLine());
                        EventDTO newEvent = AdminEventFactory.createEvent(admin, eventId, eventName,eventTypeId,description,eventDate,startTime, endTime, capacity);
                        System.out.println(newEvent.getName());
                        System.out.println(newEvent.getDescription());
                        break;

                    case 8:
                        System.out.println("Exiting...");
                        DbConnectionSingleton.getInstance().close(conn, null);
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed when the application ends
            DbConnectionSingleton.getInstance().close(conn, null);
        }
    }
}