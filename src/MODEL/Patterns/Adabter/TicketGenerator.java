/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MODEL.Patterns.Adabter;

/**
 *
 * @author mahallawy
 */
public interface TicketGenerator {
 String generateTicket(); // Generate a ticket in text format
    void saveTicketToFile(String filePath); // Save the ticket to a file
}
/*
how to use example  :
Borrow borrow = new Borrow(1, userId, 14); // Example borrow details
List<Book> books = bookService.getBooksByIds(bookIds); // Retrieve books by IDs

// Initialize the adapter with borrow and book data
TicketGenerator ticketGenerator = new BorrowingAdapter(borrow, books);

// Generate and display the ticket as a string
String ticket = ticketGenerator.generateTicket();
System.out.println(ticket);

// Save the ticket to a file
String filePath = "borrow_ticket_" + borrow.getId() + ".txt";
ticketGenerator.saveTicketToFile(filePath);

System.out.println("Ticket saved to: " + filePath);


*/



