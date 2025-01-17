package MODEL.Patterns.StateAndTemplate;


import MODEL.DAO.EventDAO;
import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.Event.VolunteeringDTO;
import MODEL.DTO.Event.VolunteeringDetailsDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.Adabter.EventJoiningAdapter;
import MODEL.Patterns.Adabter.TicketGenerator;
import MODEL.Patterns.Command.Manager.VolunteringManager;
import View.InputHandler;
import View.UserView;
import View.UtilityHandler;
import java.sql.SQLException;

public abstract class EventJoiningTemplateContext {
    protected UserDTO loggedInUser;
    protected EventDTO event;
    protected UtilityHandler UI;
    protected InputHandler inputHandler;
    protected VolunteringManager volunteeringManager;
    protected EventJoiningState currentState;
    
    public EventJoiningTemplateContext(UserDTO loggedInUser) {
            this.loggedInUser = loggedInUser;
            this.UI = new UtilityHandler();
            this.inputHandler = new InputHandler();
            this.currentState = new requestEventState();  // Initial state
    }
    // Template method defining the steps
    public final void joinEvent() throws SQLException {
        requestEvent();

        checkAvailability();
        handleSpecificEventActions();  // Hook for event-specific actions
        requestVolunteeringDetails();
        addVolunteeringRecord();
        
        generateTicket();
    }
    public void setState(EventJoiningState state){
        this.currentState =state;
        
    }
    // Common steps
    private void requestEvent() throws SQLException {
       this.currentState.handle(this);
    }
    
    private void checkAvailability() throws SQLException {
       this.currentState.handle(this);

    }

    protected abstract void handleSpecificEventActions();
    
    

    private void requestVolunteeringDetails() {
       this.currentState.handle(this);
    }

    private void addVolunteeringRecord() {
        this.currentState.handle(this);
    }

    private void generateTicket() {
        this.currentState.handle(this);
    }

    // Abstract method for specific actions
  
}
