package MODEL.Patterns.RoleHandlerStrategy;
import Controller.UserController;
import MODEL.DAO.EventDAO;
import MODEL.DAO.VolunteeringDAO;
import MODEL.DAO.VolunteeringDetailsDAO;
import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.Event.VolunteeringDTO;
import MODEL.DTO.Event.VolunteeringDetailsDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Adabter.EventJoiningAdapter;
import MODEL.Patterns.Adabter.TicketGenerator;
import MODEL.Patterns.singleton.DbConnectionSingleton;
import View.UserView;
import java.sql.SQLException;

public class VolunteerRoleHandlerStrategy implements RoleHandlerStrategy {
    
    private final UserController userController;
    
    public VolunteerRoleHandlerStrategy(UserController userController) {
        this.userController = userController;
    }
    @Override
    public boolean processChoice(int choice, UserDTO loggedInUser , UserView userView) throws SQLException {
        switch (choice) {
            case 1:
                // Volunteer-specific logic for "Add Donation"
                               userController.processDonation(loggedInUser);
                break;
            case 2:
                // Volunteer-specific logic for "Create Event"
                userController.createEvent(loggedInUser);
                
                break;
            case 3:
                // Volunteer-specific logic for "Delete Event"
                
                userController.deleteEvent();
                break;
            case 4:
        int eventId = Integer.parseInt(userView.getInput("Enter the ID of the event you wish to join: "));
        int volunteerHours = Integer.parseInt(userView.getInput("Enter how many hours you are willing to volunteer for: "));

                        String status = "pending";
                       
    EventDTO event = EventDAO.getEventById(eventId);
    if (event == null) {
        System.out.println("Event not found. Please check the ID.");
        break;
    }

    // Check if the event is full  and it still need fixes
    if (EventDAO.isEventFull(eventId)) {
        System.out.println("Sorry, this event is already full.");
        break;
    }

    //  volunteering record
    VolunteeringDTO newVolunteering = new VolunteeringDTO(eventId, loggedInUser.getId());
    VolunteeringDAO.addVolunteering(newVolunteering);

    //  volunteering details
    VolunteeringDetailsDTO details = new VolunteeringDetailsDTO(eventId, loggedInUser.getId(), volunteerHours, status);
    VolunteeringDetailsDAO.addVolunteeringDetails(details);

    // Generate  ticketss
    TicketGenerator eventTicket = new EventJoiningAdapter(details, event);
    eventTicket.saveTicketToFile("G:/Spring24/this summer/last fall/sdp/tickets/event_ticket_" + loggedInUser.getId() + ".txt");

    System.out.println("You have successfully joined the event. Your ticket has been saved.");
    break;
                        
                        
                       
                        
               case 5:
                
                userView.showMessage("Logging out...");
                return true; // This will exit the menu loop
            case 6:
                userView.showMessage("Exiting...");//
                DbConnectionSingleton.getInstance().close(null, null);
                System.exit(0);
                break;

            default:
                break;
        }
        return false;
    }


}

