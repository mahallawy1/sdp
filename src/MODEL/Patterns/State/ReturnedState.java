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

public class ReturnedState implements BookState {
    private final BookDAO bookDAO = new BookDAO();

@Override
public void handleNextAction(BookContext context, BorrowedBookCollection borrowedBooks) {
    try {
        // Iterate over the borrowed books collection
        BookIterator iterator = borrowedBooks.createIterator();
        while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();
            if ("returned".equals(bookDTO.getStatus())) {
                // In this case, let's mark the book as available
                bookDTO.setStatus("available");
                bookDAO.updateBook(bookDTO);
                context.UI.showMessage("Returned book marked as available: " + bookDTO.getTitle());

                // Transition to AvailableState after making the book available
                context.setState(new AvailableState());
            }
        }
    } catch (Exception e) {
        context.UI.showMessage("Error handling action on returned book: " + e.getMessage());
    }
}
    @Override
    public void handlePreviousAction(BookContext context, BorrowedBookCollection borrowedBooks) {
        try {
            // Iterate over the borrowed books collection
            BookIterator iterator = borrowedBooks.createIterator();
            while (iterator.hasNext()) {
                BookDTO bookDTO = iterator.next();
                if ("returned".equals(bookDTO.getStatus())) {
                    // Prevent the book from being marked as overdue
                    context.UI.showMessage("Returned books cannot be marked as overdue.");

                    // Set the status to "returned" directly
                    bookDTO.setStatus("returned");
                    bookDAO.updateBook(bookDTO);  // Update book in the database
                    context.setState(new ReturnedState()); // Transition to ReturnedState
                    context.UI.showMessage("Returned book status confirmed: " + bookDTO.getTitle());
                }
            }
        } catch (Exception e) {
            context.UI.showMessage("Error handling previous action on returned book: " + e.getMessage());
        }
    }


}