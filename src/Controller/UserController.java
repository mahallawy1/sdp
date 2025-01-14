package Controller;

import MODEL.DAO.UserDAO;
import MODEL.DTO.User.UserDTO;
import View.UserView;
import MODEL.DAO.*;
import MODEL.DAO.DonationRecordDAO;
import MODEL.DAO.EventDAO;
import MODEL.Patterns.Observer.DonationObserver;
import MODEL.Patterns.Observer.DonationSubject;
import MODEL.Patterns.Observer.EventObserver;
import MODEL.Patterns.Observer.EventSubject;
import MODEL.Patterns.decorator.*;
import MODEL.Patterns.singleton.DbConnectionSingleton;
import MODEL.DAO.RoleDAO;
import MODEL.DAO.SkillsDAO;
import MODEL.DAO.*;
import MODEL.DTO.User.*;
import MODEL.DTO.User.RoleDTO;
import MODEL.DTO.User.UserDTO;

import MODEL.DTO.Event.*;
import MODEL.DTO.Donation.*;

import MODEL.DTO.Event.EventDTO;
import MODEL.Patterns.LoginStrategy.EmailPasswordLoginStrategy;
import MODEL.Patterns.LoginStrategy.LoginService;
import MODEL.Patterns.LoginStrategy.MobilePhoneLoginStrategy;
import MODEL.Patterns.factory.AdminEventFactory;
import MODEL.Patterns.factory.EventFactory;
import MODEL.Patterns.factory.VolunteerEventFactory;
import MODEL.Patterns.paymentstrategy.*;
import MODEL.Patterns.paymentstrategy.PaymentMethode;
import MODEL.Patterns.paymentstrategy.PaymentStategy;
import MODEL.Patterns.paymentstrategy.FawryPayment;
import MODEL.Patterns.paymentstrategy.CreditCardPayment;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


import java.sql.SQLException;

import static Controller.testLibrary.*;
import MODEL.DTO.Book.BookDTO;
import MODEL.Patterns.Iterator.AvailableBookCollection;
import MODEL.Patterns.Iterator.BookIterator;
import MODEL.Patterns.Iterator.BorrowedBookCollection;
import MODEL.Patterns.State.BookContext;
import java.time.LocalDateTime;

// Controller/UserController.java
public class UserController {
    
    private UserDAO userDAO;
    private UserView userView;

    public UserController(UserDAO userDAO, UserView userView) {
        this.userDAO = userDAO;
        this.userView = userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public void handleUserMenu() throws SQLException {
        userView.showLoginMenu();
        int choice = userView.getChoice();

        switch (choice) {
            case 1:
                loginUser(); // Call login method
                break;
            case 2:
                signupUser(); // Call signup method
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);  // Exit the application
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }


    private void loginUser() throws SQLException {
        userView.showMessage("Choose login method:");
        userView.showMessage("1. Login by Email and Password");
        userView.showMessage("2. Login by Mobile Phone");

        int loginChoice = userView.getChoice();
        UserDTO loggedInUser = null;

        switch (loginChoice) {
            case 1:
                // Login by Email and Password
                String email = userView.getInputWithValidation("Enter email: " , "email");
                String password = userView.getInputWithValidation("Enter password: " , "password");
                loggedInUser = userDAO.getUserByEmailAndPassword(email, password);
                break;
            case 2:
                // Login by Mobile Phone
                String mobilePhone = userView.getInputWithValidation("Enter mobile phone: " , "phone");
                loggedInUser = userDAO.getUserByMobilePhone(mobilePhone);
                break;

            default:
                userView.showMessage("Invalid login choice.");
                return; // Exit the method on invalid choice
        }

        if (loggedInUser != null) {
            userView.showMessage("Login successful! Welcome, " + " "+ loggedInUser.getFirstname());
            // Show notifications
            if(loggedInUser.getRoleId()==1)  userView.showNotification(donationObsrv);
            if(loggedInUser.getRoleId()==2) userView.showNotification(eventObsrv4Volunteer);
            if(loggedInUser.getRoleId()==3) userView.showNotification(eventObsrv);
            userView.showMainMenu(loggedInUser); // Show menu after successful login


        } else {
            userView.showMessage("Login failed. Please check your credentials.");
        }
    }
/////////////////////////////////////////////
//donation
public void processDonation(UserDTO loggedInUser) {
    IDonation donation = new SupportUsDonation(50.0); // Base donation
    List<DonationRecordTypeDTO> donationTypes = new ArrayList<>();
    donationTypes.add(new DonationRecordTypeDTO(0, 0, "Support Us Donation", 50));

    if (userView.confirm("You have added a 50 Dollar Donation by default. Do you want to add more donations? (y/n): ")) {
        if (userView.confirm("Add Charity Donation? (y/n): ")) {
            double charityAmount = userView.getDonationAmount("Enter amount for Charity Donation: ");
            donation = new CharityDonation(donation, charityAmount);
            donationTypes.add(new DonationRecordTypeDTO(0, 0, "Charity Donation", (int) charityAmount));
        }
        if (userView.confirm("Add Gaza Donation? (y/n): ")) {
            double gazaAmount = userView.getDonationAmount("Enter amount for Gaza Donation: ");
            donation = new GazaDonation(donation, gazaAmount);
            donationTypes.add(new DonationRecordTypeDTO(0, 0, "Gaza Donation", (int) gazaAmount));
        }
        if (userView.confirm("Add Sudan Donation? (y/n): ")) {
            double sudanAmount = userView.getDonationAmount("Enter amount for Sudan Donation: ");
            donation = new SudanDonation(donation, sudanAmount);
            donationTypes.add(new DonationRecordTypeDTO(0, 0, "Sudan Donation", (int) sudanAmount));
        }
    } else {
        userView.showMessage("Processing default 50 Dollar donation...");
    }

    double cumulativeAmount = donation.getAmount();

    DonationRecordDTO donationRecord = new DonationRecordDTO();
    donationRecord.setUserId(loggedInUser.getId());
    donationRecord.setDonateDate(new Date());
    donationRecord.setCumulativeAmount((int) cumulativeAmount);
    donationRecord.setStatus(true);

    try (Connection conn = DbConnectionSingleton.getInstance().getConnection()) {
        DonationRecordDAO donationRecordDAO = new DonationRecordDAO(conn);
        int donationId = donationRecordDAO.createDonationRecord(donationRecord, donationTypes);

        if (donationId != -1) {
            userView.showMessage("Donation successfully added with cumulative amount: " + cumulativeAmount);

            int paymentChoice = userView.getPaymentChoice();

            PaymentStategy paymentStrategy = null;
            if (paymentChoice == 1) {
                paymentStrategy = new FawryPayment(userView);
            } else if (paymentChoice == 2) {
                paymentStrategy = new CreditCardPayment( userView);
            }

            if (paymentStrategy != null) {
                PaymentDTO payment = new PaymentDTO();
                payment.setPaymentMethodId(paymentChoice); // 1 = Fawry, 2 = Credit Card
                payment.setTimestamp(LocalDateTime.now());
                payment.setIsDeleted(false);

                PaymentDAO paymentDAO = new PaymentDAO(conn);
                int paymentId = paymentDAO.createPayment(payment, paymentStrategy);
                paymentDAO.linkDonationToPayment(donationId, paymentId);

                userView.showMessage("Payment processed successfully with Payment ID: " + paymentId);
                donationSubj.setNotification(loggedInUser.getFirstname(), cumulativeAmount);
            } else {
                userView.showMessage("Invalid payment method selected.");
            }
        } else {
            userView.showMessage("Failed to create donation record.");
        }
    } catch (Exception e) {
        userView.showMessage("An error occurred: " + e.getMessage());
    }
}


    private void processPayment(UserDTO user, double amount) {
        int paymentChoice = userView.getPaymentChoice();
        PaymentStategy paymentStrategy = switch (paymentChoice) {
            case 1 -> new FawryPayment(userView );
            case 2 -> new CreditCardPayment(userView);
            default -> null;
        };

        if (paymentStrategy != null) {
            PaymentMethode paymentService = new PaymentMethode(paymentStrategy);
            paymentService.executePayment(new PaymentDTO());
            userView.showMessage("Payment processed successfully.");
            // Notify observers if needed

        } else {
            userView.showMessage("Invalid payment choice. No payment processed.");
        }
    }
/////////////////////////////////////////////////////
    ///event
    
    public void createEvent(UserDTO loggedInUser) {
        EventFactory ev;

        if (loggedInUser.getRoleId() == 2) {
            // Volunteer
            ev = new VolunteerEventFactory(userView); 
        } else if (loggedInUser.getRoleId() == 1) { 
            // Admin
            ev = new AdminEventFactory(userView);

        } else {
            userView.showMessage("Invalid role, cannot create event.");
            return;
        }

        String eventName = userView.getEventName();
        int eventTypeId = userView.getEventTypeId(loggedInUser.getRoleId());
        String description = userView.getEventDescription();

        LocalDate eventDate = userView.getEventDate();

        LocalTime startTime = userView.getStartTime();
        LocalTime endTime = userView.getEndTime();
        ArrayList <Integer> skills = userView.getSkills();
        EventDTO newEvent = ev.createEvent(loggedInUser, eventName, eventTypeId, description, eventDate, startTime, endTime,skills);
        
        userView.showMessage("Event created: " + newEvent.getName());
        userView.showMessage("Description: " + newEvent.getDescription());
        eventSubj.setNotification(eventName, eventDate, startTime, endTime, description);

    }
    //////////////////////////delete event/////////////////
    public void deleteEvent() {
        int eventId = userView.getEventIdForDeletion();

        try {
            EventDAO.removeEvent(eventId);
            userView.showMessage("Event with ID " + eventId + " removed successfully.");
        } catch (SQLException e) {
            userView.showMessage("Error removing event: " + e.getMessage());
        }
    }

    public void addBook(){
       try{    
        BookDAO bookDAO = new BookDAO();
        String description = userView.getInputWithValidation("Enter book description:", "text");
        String title = userView.getInputWithValidation("Enter book title" , "text");
        String cover = userView.getInputWithValidation("Enter book cover URL:", "text");
        String publishYear = userView.getInputWithValidation("Enter publish year:", "text");
        String quantity = userView.getInputWithValidation("Enter quantity:", "text");
        String status = "available";
        BookDTO bookDTO = new BookDTO(0,description,title,cover,false,Integer.parseInt(publishYear),Integer.parseInt(quantity),status);
       
        bookDAO.addBook(bookDTO);
        System.out.println("Book added Successfully");
       }
       catch(Exception e){
           System.out.println("Error adding book" + e);
       }
    }
    public void deleteBook(){
       try{
        String id = userView.getInputWithValidation("Enter the ID of the book to delete:","text");
        BookDAO bookDAO = new BookDAO();
        bookDAO.deleteBook(Integer.parseInt(id));
        System.out.println("test");
       }
       catch(Exception e){
           System.out.println("Error deleting book " + e);
       }
    }
    public void displayAvailableBooks() {
        try {
            // Create an instance of BookDAO to fetch books
            BookDAO bookDAO = new BookDAO();

            // Fetch all books into an AvailableBookCollection
            AvailableBookCollection availableBooks = bookDAO.getAllBooks();

            // Create an iterator to traverse the collection
            BookIterator iterator = availableBooks.createIterator();

            // Iterate through the books and display their details
            while (iterator.hasNext()) {
                BookDTO book = iterator.next();
                System.out.println("ID: " + book.getId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Description: " + book.getDescription());
                System.out.println("Publish Year: " + book.getPublishYear());
                System.out.println("Quantity: " + book.getQuantity());
                System.out.println("Status: " + book.getStatus());
                System.out.println("-----------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error displaying books: " + e.getMessage());
        }
        }    
        public void borrowBooks(UserDTO loggedInUser) {
            // Display available books
            displayAvailableBooks();

            // Initialize the BorrowedBookCollection to store the books being borrowed
            BorrowedBookCollection borrowedBooks = new BorrowedBookCollection();

            // Loop to allow user to enter multiple book IDs
            while (true) {
                // Prompt user to enter the book id they wish to borrow in a user-friendly way
                String bookId = userView.getInputWithValidation("Please enter the book ID you wish to borrow (or 'done' to finish):", "bookId");

                // Check if the user is done entering books
                if (bookId.equalsIgnoreCase("done")) {
                    System.out.println("You have finished entering books.");
                    break;
                }

                try {
                    // Create BookDAO to fetch book data from the database or collection
                    BookDAO bookDAO = new BookDAO();
                    BookDTO bookDTO = bookDAO.getBookById(Integer.parseInt(bookId));

                    // Check if the book is available and not deleted
                    if (bookDTO != null && !bookDTO.getDeleted()) {
                        // Add the book to the borrowed books collection
                        borrowedBooks.addBook(bookDTO);

                        // Inform the user
                        System.out.println("Book '" + bookDTO.getTitle() + "' has been added to your borrowed list.");
                    } else {
                        System.out.println("This book is either deleted or unavailable.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid book ID. Please enter a valid numeric ID.");
                } catch (Exception e) {
                    System.out.println("Error adding the book: " + e.getMessage());
                }
            }

    // Once all books are added, pass the BorrowedBookCollection to BookContext for processing
            
                // Create a BookContext for the entire BorrowedBookCollection
                BookContext bookContext = new BookContext(borrowedBooks);

                // Now, process the books in the BorrowedBookCollection using the methods in BookContext
                bookContext.requestBook();
                bookContext.reserveBook();
                bookContext.checkoutBook(loggedInUser.getId());
                bookContext.markOverdue();
                // Optionally, bookContext.returnBooks(); can be called if needed

                // Inform the user that the books have been successfully borrowed
                System.out.println("All selected books have been successfully borrowed.");
           
            }
        
    /////////////////////////////////////////////////////
    ///////////////////////////////////////
    ///delete ust 
    public void deleteusr() {
        int deleteUserId = userView.getUsrIdForDeletion();

        try {
            UserDAO.deleteUser(deleteUserId);
            userView.showMessage("user with ID " + deleteUserId + " removed successfully.");
        } catch (SQLException e) {
            userView.showMessage("Error removing usr: " + e.getMessage());
        }
    }
    /* System.out.print("Enter user ID to delete: ");
                        int deleteUserId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        boolean userTodelete = UserDAO.deleteUser(deleteUserId);
                     System.out.println("User deleted: " + userTodelete);
                      //delete here */
    ///////////////////////////////////////////
    private void signupUser() {
        // Signup logic
        String signupEmail = userView.getInputWithValidation("Enter email: ", "email");
        String signupPassword = userView.getInputWithValidation("Enter password: ", "password");
        String firstName = userView.getInputWithValidation("Enter first name: ", "text");
        String mobilePhone = userView.getInputWithValidation("Enter mobile phone: ", "phone");
        String addressIdInput = userView.getInputWithValidation("Enter address ID: ", "addressid");
        String roleIdInput = userView.getInputWithValidation("Are you An volunteer or a member chose 1 or 2 respectively: ", "role");
        // what is the status i don't know !!
        String statusInput = userView.getInputWithValidation("enter the status: ", "status");

        UserDTO newUser = new UserDTO();
        // Set user data
        newUser.setEmail(signupEmail);
        newUser.setPassword(signupPassword);
        newUser.setFirstname(firstName);
        newUser.setMobilePhone(mobilePhone);


        try {
            // Converting addressId and roleId from String to Integer
            int addressId = Integer.parseInt(addressIdInput);
            int roleId = Integer.parseInt(roleIdInput);
            int status = Integer.parseInt(statusInput);
            // increment by 1 because in the db 1 is the admin , 2 is the author and 3 is the donor !!
            // no admin account should be created here
            roleId++;
            // Set these values in the UserDTO
            newUser.setAddressId(addressId);
            newUser.setRoleId(roleId);
            newUser.setStatus(status);



            boolean isAdded = userDAO.addUser(newUser);

            if (isAdded) {
                userView.showMessage("Signup successful!");
                userView.showMainMenu(newUser);  // Show the main menu after successful signup
            } else {
                userView.showMessage("Signup failed. Please try again.");
            }
        } catch (NumberFormatException | SQLException e) {
            userView.showMessage("Invalid input format for address ID, role ID, or status. Please ensure they are correct.");
        }
    }

    public void handleMainMenu(UserDTO loggedInUser) throws SQLException {
        userView.showMainMenu(loggedInUser);
        // Handle each menu item
    }

    // Other controller methods for handling user actions (add donation, create event, etc.)
}
