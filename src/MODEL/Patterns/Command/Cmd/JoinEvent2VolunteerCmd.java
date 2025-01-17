package MODEL.Patterns.Command.Cmd;

import MODEL.DTO.Event.VolunteeringDTO;
import MODEL.DTO.Event.VolunteeringDetailsDTO;
import MODEL.Patterns.Command.ICmd;
import MODEL.Patterns.Command.ICommand;
import MODEL.Patterns.Command.Manager.VolunteringManager;

import java.sql.SQLException;

public class JoinEvent2VolunteerCmd implements ICmd {
    public VolunteringManager volunteringManager;
    public VolunteeringDTO newVolunteering;
    public VolunteeringDetailsDTO details;

    public JoinEvent2VolunteerCmd(VolunteringManager volunteringManager, VolunteeringDTO newVolunteering, VolunteeringDetailsDTO details) {
        this.volunteringManager = volunteringManager;
        this.newVolunteering = newVolunteering;
        this.details = details;
    }

    @Override
    public Object execute() throws SQLException {
        return volunteringManager.joinEvent2Volunteer(newVolunteering, details);
    }
}
