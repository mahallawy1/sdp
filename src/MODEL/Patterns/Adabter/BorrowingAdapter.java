/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Adabter;

/**
 *
 * @author mahallawy
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BorrowingAdapter implements TicketGenerator {
    private final Borrow borrow;
    private final List<Book> books;

    // Constructor initializes the required data
    public BorrowingAdapter(Borrow borrow, List<Book> books) {
        this.borrow = borrow;
        this.books = books;
    }

    @Override
    public String generateTicket() {
        StringBuilder ticket = new StringBuilder();
        ticket.append("=== Borrow Ticket ===\n");
        ticket.append("Borrow ID: ").append(borrow.getId()).append("\n");
        ticket.append("User ID: ").append(borrow.getUserId()).append("\n");
        ticket.append("Days: ").append(borrow.getDays()).append("\n");
        ticket.append("Books:\n");

        for (Book book : books) {
            ticket.append("- ").append(book.getTitle())
                  .append(" (").append(book.getDescription()).append(")\n");
        }

        ticket.append("=====================\n");
        return ticket.toString();
    }

    @Override
    public void saveTicketToFile(String filePath) {
        String ticketContent = generateTicket();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(ticketContent);
        } catch (IOException e) {
            System.err.println("Error saving ticket to file: " + e.getMessage());
        }
    }
}
