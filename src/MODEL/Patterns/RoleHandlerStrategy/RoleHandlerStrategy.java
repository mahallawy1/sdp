package MODEL.Patterns.RoleHandlerStrategy;
import MODEL.DTO.User.UserDTO;
import View.InputHandler;
import View.UserView;
import View.UtilityHandler;

import java.sql.SQLException;

public interface RoleHandlerStrategy {
    boolean processChoice(int choice, UserDTO loggedInUser, UtilityHandler ui,InputHandler inputHandler) throws SQLException;
}

