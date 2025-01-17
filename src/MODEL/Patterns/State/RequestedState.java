/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.State;

import MODEL.DAO.BookDAO;
import MODEL.DTO.Book.BookDTO;
import MODEL.Patterns.Iterator.BookIterator;
import MODEL.Patterns.Iterator.BorrowedBookCollection;


/**
 *
 * @author hussien
 */

public class RequestedState implements BookState {
    BookDAO bookDAO = new BookDAO();

     @Override
    public void handleNextAction(BookContext context, BorrowedBookCollection borrowedBooks) {
        try {
            BookIterator iterator = borrowedBooks.createIterator();
            while (iterator.hasNext()) {
                BookDTO bookDTO = iterator.next();
                if ("requested".equals(bookDTO.getStatus())) {
                    bookDTO.setStatus("reserved");
                    bookDAO.updateBook(bookDTO);
                    context.UI.showMessage("Book reserved for the user: " + bookDTO.getTitle());
                }
            }
            // Transition to ReservedState after handling
            context.setState(new ReservedState());
        } catch (Exception e) {
            context.UI.showMessage("Error reserving book: " + e.getMessage());
        }
    }

    @Override
    public void handlePreviousAction(BookContext context, BorrowedBookCollection borrowedBooks) {
        // Implement logic for canceling the request and going back to AvailableState
        context.UI.showMessage("Cancelling the request and returning to Available state.");
        try {
            BookIterator iterator = borrowedBooks.createIterator();
            while (iterator.hasNext()) {
                BookDTO bookDTO = iterator.next();
                if ("requested".equals(bookDTO.getStatus())) {
                    bookDTO.setStatus("available");  // Marking the book as available again
                    bookDAO.updateBook(bookDTO);
                    context.UI.showMessage("Request for book canceled: " + bookDTO.getTitle());
                }
            }
            context.setState(new AvailableState());  // Transition back to AvailableState
        } catch (Exception e) {
            context.UI.showMessage("Error canceling request: " + e.getMessage());
        }
    }
}