/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Adabter;

import MODEL.DTO.Book.BookDTO;
import MODEL.DTO.Book.BorrowDTO;
import MODEL.Patterns.Iterator.BookIterator;
import MODEL.Patterns.Iterator.BorrowedBookCollection;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author mahallawy
 */


public class BorrowingAdapter implements TicketGenerator {
    private final BorrowDTO borrow;
    private final BorrowedBookCollection borrowedBooks;

    // Constructor initializes the required data
    public BorrowingAdapter(BorrowDTO borrow, BorrowedBookCollection borrowedBooks) {
        this.borrow = borrow;
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public String generateTicket() {
        StringBuilder ticket = new StringBuilder();
        ticket.append("=== Borrow Ticket ===\n");
        ticket.append("Borrow ID: ").append(borrow.getId()).append("\n");
        ticket.append("User ID: ").append(borrow.getUserId()).append("\n");
        ticket.append("Days: ").append(borrow.getDays()).append("\n");
        ticket.append("Books:\n");

        // Iterate over all books in the borrowed collection and append to the ticket
        BookIterator iterator = borrowedBooks.createIterator();
        while (iterator.hasNext()) {
            BookDTO book = iterator.next();
            ticket.append("- ").append(book.getTitle()).append(" (").append(book.getDescription()).append(")\n");
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