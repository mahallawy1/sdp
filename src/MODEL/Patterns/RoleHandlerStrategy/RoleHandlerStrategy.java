package MODEL.Patterns.RoleHandlerStrategy;
import MODEL.DTO.User.UserDTO;
import View.UserView;

import java.sql.SQLException;

public interface RoleHandlerStrategy {
    boolean processChoice(int choice, UserDTO loggedInUser, UserView userView) throws SQLException;
}

