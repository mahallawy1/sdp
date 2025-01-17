/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author hussien
 */


import MODEL.DAO.SkillsDAO;
import MODEL.DTO.Event.SkillDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventView {
    private Scanner scanner;
    UtilityHandler UI;

    public EventView() {
        this.scanner = new Scanner(System.in);
        this.UI = new UtilityHandler();
    }

   public String getEventName() {
        System.out.println("Enter Event Name:");
        return scanner.nextLine();
    }



    public int getEventTypeId() {
        Scanner scanner = new Scanner(System.in);
        int eventTypeId = -1; // Default to an invalid value

        // Prompt the user for input
        System.out.println("Choose Event Type:");
        System.out.println("1 - Workshop");
        System.out.println("2 - Seminar");

        while (true) {
            try {
                System.out.print("Enter your choice: ");
                eventTypeId = Integer.parseInt(scanner.nextLine());

                if (eventTypeId == 1|| eventTypeId == 2) {
                    break; // Valid choice
                } else {
                    System.out.println("Invalid choice. Please enter 0 for Seminar or 1 for Workshop.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (0 or 1).");
            }
        }

        return eventTypeId;
    }
    public String getEventDescription() {
        System.out.println("Enter Event Description:");
        return scanner.nextLine();
    }

    public LocalDate getEventDate() {
        LocalDate eventDate;
        while (true) {
            System.out.println("Enter Event Date (yyyy-mm-dd):");
            try {
                eventDate = LocalDate.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                this.UI.showMessage("Invalid date format. Please use yyyy-mm-dd.");
            }
        }
        return eventDate;
    }

    public LocalTime getStartTime() {
        LocalTime startTime;
        while (true) {
            System.out.println("Enter Start Time (HH:mm):");
            try {
                startTime = LocalTime.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                this.UI.showMessage("Invalid time format. Please use HH:mm.");
            }
        }
        return startTime;
    }

    public LocalTime getEndTime() {
        LocalTime endTime;
        while (true) {
            System.out.println("Enter End Time (HH:mm):");
            try {
                endTime = LocalTime.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                this.UI.showMessage("Invalid time format. Please use HH:mm.");
            }
        }
        return endTime;
    }    public static ArrayList<Integer> displayAndChooseSkills() {
        ArrayList<Integer> selectedSkillIds = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        try {
            // Fetch all skills from the database
            List<SkillDTO> skills = SkillsDAO.getAllSkills();

            if (skills.isEmpty()) {
                System.out.println("No skills available.");
                return selectedSkillIds; // Return empty list
            }

            // Display the list of skills
            System.out.println("Skills List:");
            for (SkillDTO skill : skills) {
                System.out.println(skill.getId() + " - " + skill.getName());
            }

            // Prompt user to select skills by ID
            System.out.println("\nEnter the IDs of the skills you want to choose (comma-separated):");
            String input = scanner.nextLine();
            String[] skillIds = input.split(",");

            // Process the selected IDs
            for (String id : skillIds) {
                try {
                    int skillId = Integer.parseInt(id.trim());
                    SkillDTO skill = SkillsDAO.getSkillById(skillId);
                    if (skill != null) {
                        selectedSkillIds.add(skillId);
                    } else {
                        System.out.println("Skill with ID " + skillId + " not found.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + id.trim() + " is not a valid number.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while fetching skills from the database.");
        }

        return selectedSkillIds;
    }

    public int getEventIdForDeletion() {
        System.out.println("Enter the Event ID to delete:");
        return Integer.parseInt(scanner.nextLine());
    }

}
