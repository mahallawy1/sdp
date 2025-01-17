package Controller;

import MODEL.DAO.UserDAO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Command.*;
import MODEL.Patterns.Command.Cmd.*;
import MODEL.Patterns.Command.Manager.BookManager;
import MODEL.Patterns.Command.Manager.DonationManager;
import MODEL.Patterns.Command.Manager.EventManager;
import MODEL.Patterns.Command.Manager.UserManager;
import MODEL.Patterns.Iterator.AvailableBookCollection;
import MODEL.Patterns.facade.NotificationFacade;
import View.UserView;
import MODEL.DAO.*;
import MODEL.Patterns.decorator.*;
import MODEL.Patterns.singleton.DbConnectionSingleton;
import MODEL.DAO.SkillsDAO;

import MODEL.DTO.Event.*;
import MODEL.DTO.Donation.*;

import MODEL.DTO.Event.EventDTO;
import MODEL.Patterns.factory.AdminEventFactory;
import MODEL.Patterns.factory.EventFactory;
import MODEL.Patterns.factory.VolunteerEventFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static Controller.testLibrary.*;
import MODEL.DTO.Book.BookDTO;
import MODEL.Patterns.Iterator.BookIterator;
import MODEL.Patterns.Iterator.BorrowedBookCollection;
import MODEL.Patterns.Proxy.BookProxy;
import MODEL.Patterns.Proxy.IBook;
import MODEL.Patterns.State.BookContext;
import MODEL.Patterns.StateAndTemplate.EventJoiningTemplateContext;
import MODEL.Patterns.StateAndTemplate.SeminarEventJoiningContext;
import MODEL.Patterns.StateAndTemplate.WorkshopEventJoiningContext;
import MODEL.Patterns.Tepmlate.CreditCardPaymentTemplate;
import MODEL.Patterns.Tepmlate.DonationPaymentTemplate;
import MODEL.Patterns.Tepmlate.FawryPaymentTemplate;
import View.DonationView;
import View.EventView;
import View.InputHandler;
import View.NotificationView;
import View.UtilityHandler;
import java.time.LocalDateTime;
import java.util.Random;

// Controller/UserController.java
public class UserController {
    
    private UserDAO userDAO;
    private UserView userView;
    private UtilityHandler UI;
    private InputHandler inputHandler;
    private NotificationView notificationView;
    private EventView eventView;
    private DonationView donationView;
    private Invoker invoker;


    public UserController(UserDAO userDAO, UserView userView,UtilityHandler utilityHandler,InputHandler inputHandler, NotificationView notificationView, EventView eventView,DonationView donationView ) {
        this.userDAO = userDAO;
        this.userView = userView;
        this.UI = utilityHandler;
        this.inputHandler = inputHandler;
        this.notificationView = notificationView;
        this.eventView = eventView;
        this.donationView = donationView;
        this.invoker = new Invoker();

    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public void handleUserMenu() throws SQLException {
        userView.showLoginMenu();
        int choice = inputHandler.getChoice();

        switch (choice) {
            case 1:
                loginUser(); // Call login method
                break;
            case 2:
                signupUser(); // Call signup method
                break;
            case 3:
                UI.showMessage("Exiting...");
                System.exit(0);  // Exit the application
                break;
            default:
                UI.showMessage("Invalid choice. Please try again.");
        }
    }


    private void loginUser() throws SQLException {
        UI.showMessage("Choose login method:");
        UI.showMessage("1. Login by Email and Password");
        UI.showMessage("2. Login by Mobile Phone");

        int loginChoice = inputHandler.getChoice();
        UserDTO loggedInUser = null;

        // command design pattern
        UserManager userManager;
        switch (loginChoice) {
            case 1:
                // Login by Email and Password
                String email = inputHandler.getInputWithValidation("Enter email: " , "email");
                String password = inputHandler.getInputWithValidation("Enter password: " , "password");

                // command design pattern
                userManager = new UserManager();
                invoker.setCmd(new LoginByPasswordCmd(userManager, email, password));
                loggedInUser = (UserDTO) invoker.executeCmd();
                //loggedInUser = userDAO.getUserByEmailAndPassword(email, password);
                break;
            case 2:
                // Login by Mobile Phone
                String mobilePhone = inputHandler.getInputWithValidation("Enter mobile phone: " , "phone");
                // command design pattern
                userManager = new UserManager();
                invoker.setCmd(new LoginByMobilePhoneCmd(userManager, mobilePhone));
                loggedInUser = (UserDTO) invoker.executeCmd();
                //loggedInUser = userDAO.getUserByMobilePhone(mobilePhone);
                break;

            default:
                UI.showMessage("Invalid login choice.");
                return; // Exit the method on invalid choice
        }

        if (loggedInUser != null) {
            UI.showMessage("Login successful! Welcome, " + " "+ loggedInUser.getFirstname());
            // Show notifications
            if(loggedInUser.getRoleId()==1)  notificationView.showNotification(donationObsrv);
            if(loggedInUser.getRoleId()==2) notificationView.showNotification(eventObsrv4Volunteer);
            if(loggedInUser.getRoleId()==3) notificationView.showNotification(eventObsrv);
            userView.showMainMenu(loggedInUser); // Show menu after successful login


        } else {
           UI.showMessage("Login failed. Please check your credentials.");
        }
    }
/////////////////////////////////////////////
//donation
public void processDonation(UserDTO loggedInUser) {
    IDonation donation = new SupportUsDonation(50.0); // Base donation
    List<DonationRecordTypeDTO> donationTypes = new ArrayList<>();
    donationTypes.add(new DonationRecordTypeDTO(0, 0, "Support Us Donation", 50));

    if (donationView.confirm("You have added a 50 Dollar Donation by default. Do you want to add more donations? (y/n): ")) {
        if (donationView.confirm("Add Charity Donation? (y/n): ")) {
            double charityAmount = donationView.getDonationAmount("Enter amount for Charity Donation: ");
            donation = new CharityDonation(donation, charityAmount);
            donationTypes.add(new DonationRecordTypeDTO(0, 0, "Charity Donation", (int) charityAmount));
        }
        if (donationView.confirm("Add Gaza Donation? (y/n): ")) {
            double gazaAmount = donationView.getDonationAmount("Enter amount for Gaza Donation: ");
            donation = new GazaDonation(donation, gazaAmount);
            donationTypes.add(new DonationRecordTypeDTO(0, 0, "Gaza Donation", (int) gazaAmount));
        }
        if (donationView.confirm("Add Sudan Donation? (y/n): ")) {
            double sudanAmount = donationView.getDonationAmount("Enter amount for Sudan Donation: ");
            donation = new SudanDonation(donation, sudanAmount);
            donationTypes.add(new DonationRecordTypeDTO(0, 0, "Sudan Donation", (int) sudanAmount));
        }
    } else {
       UI.showMessage("Processing default 50 Dollar donation...");
    }

    double cumulativeAmount = donation.getAmount();

    DonationRecordDTO donationRecord = new DonationRecordDTO();
    donationRecord.setUserId(loggedInUser.getId());
    donationRecord.setDonateDate(new Date());
    donationRecord.setCumulativeAmount((int) cumulativeAmount);
    donationRecord.setStatus(true);

    try (Connection conn = DbConnectionSingleton.getInstance().getConnection()) {

        // commmand design pattern
        DonationManager donationManager = new DonationManager();
        invoker.setCmd(new AddDonationCmd(donationManager, donationRecord, donationTypes));
        int donationId = (int) invoker.executeCmd();

        if (donationId != -1) {
            
            donationRecord.setId(donationId);
            UI.showMessage("Donation successfully added with cumulative amount: " + cumulativeAmount);

            int paymentChoice = donationView.getPaymentChoice();
            DonationPaymentTemplate paymentTemplate;
             
            if (paymentChoice == 1) {
                paymentTemplate = new FawryPaymentTemplate(userView, conn);
            } else if (paymentChoice == 2) {
                paymentTemplate = new CreditCardPaymentTemplate(userView, conn);
            } else {
                UI.showMessage("Invalid payment method selected.");
                return;
            }

            PaymentDTO payment = new PaymentDTO();
            payment.setPaymentMethodId(paymentChoice); // 1 = Fawry, 2 = Credit Card
            payment.setTimestamp(LocalDateTime.now());
            payment.setIsDeleted(false);

            PaymentDAO paymentDAO = new PaymentDAO(conn);
            int paymentId = paymentDAO.createPayment(payment); 
            payment.setId(paymentId);
            
            
            // Process the payment with template
            paymentTemplate.processPayment(donationRecord, payment);

            // Link donation and payment in the database
            paymentDAO.linkDonationToPayment(donationId, paymentId);
             
            
              
            UI.showMessage("Payment processed successfully with Payment ID: " + paymentId);
            donationSubj.setNotification(loggedInUser.getFirstname(), cumulativeAmount);
            NotificationFacade notificationFacade = new NotificationFacade();
            notificationFacade.sendThankYouEmail(loggedInUser.getEmail(), cumulativeAmount);
        } else {
            UI.showMessage("Failed to create donation record.");
             
        }
    } catch (Exception e) {
        UI.showMessage("An error occurred: " + e.getMessage());
    }
}


//    private void processPayment(UserDTO user, double amount) {
//        int paymentChoice = donationView.getPaymentChoice();
//        PaymentStategy paymentStrategy = switch (paymentChoice) {
//            case 1 -> new FawryPayment( );
//            case 2 -> new CreditCardPayment();
//            default -> null;
//        };
//
//        if (paymentStrategy != null) {
//            PaymentMethode paymentService = new PaymentMethode(paymentStrategy);
//            paymentService.executePayment(new PaymentDTO());
//            UI.showMessage("Payment processed successfully.");
//            // Notify observers if needed
//
//        } else {
//            UI.showMessage("Invalid payment choice. No payment processed.");
//        }
//    }
/////////////////////////////////////////////////////
    ///event
     public  void setSkills() {
        // Predefined random skills for library events
        String[] randomSkills = {
            "Book Cataloging",
            "Research Assistance",
            "Event Organization",
            "Storytelling",
            "Digital Library Management",
            "Data Analysis",
            "Customer Service",
            "Public Speaking",
            "Archiving",
            "Multimedia Management"
        };

        Random random = new Random();

        try {
            // Generate random skills and add them to the database
            for (int i = 1; i <= 10; i++) { // Add 10 random skills
                String skillName = randomSkills[random.nextInt(randomSkills.length)];
                SkillDTO skill = new SkillDTO(i, skillName); // Use i as a unique ID
                if (SkillsDAO.addSkill(skill)) {
                    UI.showMessage("Skill added: " + skillName);
                } else {
                    UI.showMessage("Failed to add skill: " + skillName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            UI.showMessage("Error while adding skills to the database.");
        }
    }
    public void createEvent(UserDTO loggedInUser) {
       // setSkills();
       try{
       //EventTypeDAO eventTypeDAO = new EventTypeDAO();
       //eventTypeDAO.initializeEventTypes();
        EventFactory ev;

        if (loggedInUser.getRoleId() == 2) {
            // Volunteer
            ev = new VolunteerEventFactory(); 
        } else if (loggedInUser.getRoleId() == 1) { 
            // Admin
            ev = new AdminEventFactory();

        } else {
            UI.showMessage("Invalid role, cannot create event.");
            return;
        }

        String eventName = eventView.getEventName();
        int eventTypeId = eventView.getEventTypeId();
        String description = eventView.getEventDescription();

        LocalDate eventDate = eventView.getEventDate();

        LocalTime startTime = eventView.getStartTime();
        LocalTime endTime = eventView.getEndTime();
        ArrayList <Integer> skills = eventView.displayAndChooseSkills();
        EventDTO newEvent = ev.createEvent(loggedInUser, eventName, eventTypeId, description, eventDate, startTime, endTime,skills);
        
        UI.showMessage("Event created: " + newEvent.getName());
        UI.showMessage("Description: " + newEvent.getDescription());
        eventSubj.setNotification(eventName, eventDate, startTime, endTime, description);
       }
       catch(Exception e){
           UI.showMessage("Error creating event : " + e);
       }
    }
    
    //////////////////////////delete event/////////////////
    public void deleteEvent() throws SQLException {
        int eventId = eventView.getEventIdForDeletion();


        try {
            //EventDAO.removeEvent(eventId);
            // Command design patten
            EventManager eventManager = new EventManager();
            invoker.setCmd(new DeleteEventCmd(eventManager, eventId));
            if((boolean) invoker.executeCmd())
                UI.showMessage("Event with ID " + eventId + " removed successfully.");
            else
                UI.showMessage("Error removing event with ID "+ eventId +". Maybe id was wrong.");
        } catch (SQLException e) {
            UI.showMessage("Error removing event: " + e.getMessage());
        }
    }
      public void joinEvent(UserDTO loggedInUser){
// try{
//                int eventId = Integer.parseInt(userView.getInput("Enter the ID of the event you wish to join: "));
//                int volunteerHours = Integer.parseInt(userView.getInput("Enter how many hours you are willing to volunteer for: "));
//
//                String status = "pending";
//
//                EventDTO event = EventDAO.getEventById(eventId);
//                if (event == null) {
//                    userView.showMessage("Event not found. Please check the ID.");
//                    return;
//                }
//
//                // Check if the event is full  and it still need fixes
//                if (EventDAO.isEventFull(eventId)) {
//                    userView.showMessage("Sorry, this event is already full.");
//                    return;
//                }
//
//                //  volunteering record
//                VolunteeringDTO newVolunteering = new VolunteeringDTO(eventId, loggedInUser.getId());
//                //VolunteeringDAO.addVolunteering(newVolunteering);
//
//                //  volunteering details
//                VolunteeringDetailsDTO details = new VolunteeringDetailsDTO(eventId, loggedInUser.getId(), volunteerHours, status);
//                //VolunteeringDetailsDAO.addVolunteeringDetails(details);
//
//                // command design pattern
//                VolunteringManager volunteringManager = new VolunteringManager(newVolunteering, details);
//                invoker.setCommand(new JoinEvent2VolunteerCmd(volunteringManager));
//                invoker.execute();
//                if (volunteringManager.isSuccessful()) {
//                    // Generate  ticketss
//                    TicketGenerator eventTicket = new EventJoiningAdapter(details, event);
//                    eventTicket.saveTicketToFile("G:/Spring24/this summer/last fall/sdp/tickets/event_ticket_" + loggedInUser.getId() + ".txt");
//                    userView.showMessage("You have successfully joined the event. Your ticket has been saved.");
//                } else
//                    userView.showMessage("Error joining event.");
//        }catch(Exception e){
//            userView.showMessage("Error joining event " +e);
//        }
       
    try{ 
      String event_type = inputHandler.getInput("Enter which type of events you wish to join 1 - workshop , 2 - seminar : ");
      if(Integer.parseInt(event_type) == 1 ){
       eventView.displayWorkshopEvents();
       
       EventJoiningTemplateContext eventContext = new WorkshopEventJoiningContext(loggedInUser);
       eventContext.joinEvent();

      }else if(Integer.parseInt(event_type)==2){
      eventView.displaySeminarEvents();
      EventJoiningTemplateContext eventContext = new SeminarEventJoiningContext(loggedInUser);
      eventContext.joinEvent();
      }else{
          UI.showMessage("invalid choice");
      }
           
      
    }
    catch(Exception e){
        UI.showMessage("error joining event : " + e);
    }
    }
    public void addBook(){
       try{    
        //BookDAO bookDAO = new BookDAO();
        String description = inputHandler.getInputWithValidation("Enter book description:", "text");
        String title = inputHandler.getInputWithValidation("Enter book title" , "text");
        String cover = inputHandler.getInputWithValidation("Enter book cover URL:", "text");
        String publishYear = inputHandler.getInputWithValidation("Enter publish year:", "text");
        String quantity = inputHandler.getInputWithValidation("Enter quantity:", "text");
        String status = "available";
        BookDTO bookDTO = new BookDTO(0,description,title,cover,false,Integer.parseInt(publishYear),Integer.parseInt(quantity),status);

        // Command design patten
        //bookDAO.addBook(bookDTO);
        BookManager bookManager = new BookManager();
        invoker.setCmd(new AddBookCmd(bookManager, bookDTO));

        if((Boolean )invoker.executeCmd())
            UI.showMessage("Book added Successfully");

       }
       catch(Exception e){
           UI.showMessage("Error adding book" + e);
       }
    }
    public void deleteBook(){
       try{
        String id = inputHandler.getInputWithValidation("Enter the ID of the book to delete:","text");


        // Command design patten
        //BookDAO bookDAO = new BookDAO();
        //bookDAO.deleteBook(Integer.parseInt(id));
        BookManager bookManager = new BookManager();
        invoker.setCmd(new DeleteBookCmd(bookManager, Integer.parseInt(id)));

        if((boolean)invoker.executeCmd())
            UI.showMessage("Book deleted successfully");
        //UI.showMessage("test");
       }
       catch(Exception e){
           UI.showMessage("Error deleting book " + e);
       }
    }
public void displayAvailableBooks(UserDTO currentUser) {
    try {
        BookManager bookManager = new BookManager();
        invoker.setCmd(new RetrieveAllBooksCmd(bookManager));
        BookIterator iterator = ((AvailableBookCollection) invoker.executeCmd()).createIterator();

        while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();

            // Create a BookProxy instance for each book
            IBook bookProxy = new BookProxy(
                bookDTO.getTitle(),
                bookDTO.getPublishYear(),
                bookDTO.getDescription(),
                bookDTO.getQuantity(),
                bookDTO.getId(),
                currentUser
            );

            // Display the book details using the proxy
            bookProxy.display();
            UI.showMessage("-----------------------------------");
        }
    } catch (Exception e) {
        UI.showMessage("Error displaying books: " + e.getMessage());
    }
}

   
        public void borrowBook(UserDTO loggedInUser) {
            displayAvailableBooks(loggedInUser);

            BorrowedBookCollection borrowedBooks = new BorrowedBookCollection();

            while (true) {
                String bookId = inputHandler.getInputWithValidation("Please enter the book ID you wish to borrow (or 'done' to finish):", "bookId");

                if (bookId.equalsIgnoreCase("done")) {
                    UI.showMessage("You have finished entering books.");
                    break;
                }

                try {
                    BookDAO bookDAO = new BookDAO();
                    BookDTO bookDTO = bookDAO.getBookById(Integer.parseInt(bookId));

                    if (bookDTO != null && !bookDTO.getDeleted()) {
                        borrowedBooks.addBook(bookDTO);

                        UI.showMessage("Book '" + bookDTO.getTitle() + "' has been added to your borrowed list.");
                    } else {
                        UI.showMessage("This book is either deleted or unavailable.");
                    }
                } catch (NumberFormatException e) {
                    UI.showMessage("Invalid book ID. Please enter a valid numeric ID.");
                } catch (Exception e) {
                    UI.showMessage("Error adding the book: " + e.getMessage());
                }
            }


                BookContext bookContext = new BookContext(borrowedBooks,loggedInUser.getId());

                bookContext.executeNextState(); // request book
                bookContext.executeNextState(); // reserve book
                bookContext.executeNextState(); // checkout book
                bookContext.executeNextState(); // markOverDue
                bookContext.executeNextState(); // return book;
                bookContext.executeNextState(); // book available again

                UI.showMessage("All selected books have been successfully borrowed.");
           
            }
        
    /////////////////////////////////////////////////////
    ///////////////////////////////////////
    ///delete ust 
    public void deleteusr() {
        int deleteUserId = userView.getUsrIdForDeletion();

        try {
            // command design pattern
            UserManager userManager = new UserManager();
            invoker.setCmd(new DeleteUserCmd(userManager, deleteUserId));
            boolean isDeleted = (boolean) invoker.executeCmd();
            //UserDAO.deleteUser(deleteUserId);
            if(isDeleted)
                UI.showMessage("user with ID " + deleteUserId + " removed successfully.");
            else
                UI.showMessage("Error removing usr with ID " + deleteUserId + ". Maybe id is wrong." );

        } catch (SQLException e) {
            UI.showMessage("Error removing usr: " + e.getMessage());
        }
    }
    /* System.out.print("Enter user ID to delete: ");
                        int deleteUserId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        boolean userTodelete = UserDAO.deleteUser(deleteUserId);
                     UI.showMessage("User deleted: " + userTodelete);
                      //delete here */
    ///////////////////////////////////////////
    private void signupUser() {
        // Signup logic
        String signupEmail = inputHandler.getInputWithValidation("Enter email: ", "email");
        String signupPassword = inputHandler.getInputWithValidation("Enter password: ", "password");
        String firstName = inputHandler.getInputWithValidation("Enter first name: ", "text");
        String mobilePhone = inputHandler.getInputWithValidation("Enter mobile phone: ", "phone");
        String addressIdInput = inputHandler.getInputWithValidation("Enter address ID: ", "addressid");
        String roleIdInput = inputHandler.getInputWithValidation("Are you An volunteer or a member chose 1 or 2 respectively: ", "role");
        // what is the status i don't know !!
        String statusInput = inputHandler.getInputWithValidation("enter the status: ", "status");

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

            // command design pattern
            UserManager userManager = new UserManager();
            invoker.setCmd(new AddUserCmd(userManager, newUser));
            //boolean isAdded = userDAO.addUser(newUser);

            if ((boolean)invoker.executeCmd()) {
                UI.showMessage("Signup successful!");
                userView.showMainMenu(newUser);  // Show the main menu after successful signup
            } else {
                UI.showMessage("Signup failed. Please try again.");
            }
        } catch (NumberFormatException | SQLException e) {
            UI.showMessage("Invalid input format for address ID, role ID, or status. Please ensure they are correct.");
        }
    }

    public void handleMainMenu(UserDTO loggedInUser) throws SQLException {
        userView.showMainMenu(loggedInUser);
        // Handle each menu item
    }

    // Other controller methods for handling user actions (add donation, create event, etc.)
}
