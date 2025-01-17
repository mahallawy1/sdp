package MODEL.Patterns.Command.Manager;

import MODEL.DAO.VolunteeringDAO;
import MODEL.DAO.VolunteeringDetailsDAO;
import MODEL.DTO.Event.VolunteeringDTO;
import MODEL.DTO.Event.VolunteeringDetailsDTO;

import java.sql.SQLException;

public class VolunteringManager {

    private boolean isSuccessful = false;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public VolunteringManager() {
   
    }
 
    public boolean joinEvent2Volunteer(VolunteeringDTO newVolunteering, VolunteeringDetailsDTO details) throws SQLException {
        try {
            if (VolunteeringDAO.addVolunteering(newVolunteering) &&
            VolunteeringDetailsDAO.addVolunteeringDetails(details))
            {
                isSuccessful = true;
                return true;
            }
            else{
                isSuccessful = false;
                return false;
            }
            
        } catch (SQLException e) {
            isSuccessful = false;
            return false;
        }


    }
}
