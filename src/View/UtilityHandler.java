/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author hussien
 */
public class UtilityHandler {
    public void showMessage(String message) {
        System.out.println(message);
    }
        
     public void displayMessageWithId(String message, int id) {
        System.out.println(message + " with ID: " + id);
    }    
    public void displayTableHeader(String format, String... headers) {
        String BLUE = "\033[34m";
        String RESET = "\033[0m";

        System.out.printf(BLUE + format + RESET, (Object[]) headers);
    }

    public void displayTableRow(String format, Object... values) {
        System.out.printf(format, values);
    }

}
