package MODEL.Patterns.Command.Manager;

import MODEL.DAO.EventDAO;
import MODEL.DAO.UserDAO;
import MODEL.DTO.Event.EventDTO;
import MODEL.DTO.User.UserDTO;
import MODEL.Patterns.LoginStrategy.EmailPasswordLoginStrategy;
import MODEL.Patterns.LoginStrategy.LoginService;
import MODEL.Patterns.LoginStrategy.MobilePhoneLoginStrategy;
import jdk.jfr.Event;

import java.sql.SQLException;
import java.util.List;

public class EventManager {

    public EventManager() {}

    public boolean deleteEvent(int id) throws  SQLException {
        return  EventDAO.removeEvent(id);
    }

}
