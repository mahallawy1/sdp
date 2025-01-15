package MODEL.Patterns.Command.Manager;

import MODEL.DAO.VolunteeringDAO;
import MODEL.DAO.VolunteeringDetailsDAO;
import MODEL.DTO.Event.VolunteeringDTO;
import MODEL.DTO.Event.VolunteeringDetailsDTO;

import java.sql.SQLException;

public class VolunteringManager {
    private VolunteeringDTO newVolunteering;
    private VolunteeringDetailsDTO details;

    private boolean isSuccessful;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public VolunteringManager(VolunteeringDTO newVolunteering, VolunteeringDetailsDTO details) {
        this.newVolunteering = newVolunteering;
        this.details = details;
    }

    public void joinEvent2Volunteer() throws SQLException {
        try {
            if (VolunteeringDAO.addVolunteering(newVolunteering) &&
            VolunteeringDetailsDAO.addVolunteeringDetails(details))
                isSuccessful = true;
            else isSuccessful = false;
        } catch (SQLException e) {
            isSuccessful = false;
        }


    }
}
