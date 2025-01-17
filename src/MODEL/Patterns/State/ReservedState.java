/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.State;

import MODEL.DAO.BookDAO;
import MODEL.DAO.BorrowDAO;
import MODEL.DAO.BorrowingDetailsDAO;
import MODEL.DTO.Book.BookDTO;
import MODEL.DTO.Book.BorrowDTO;
import MODEL.DTO.Book.BorrowDetailsDTO;
import MODEL.Patterns.Adabter.BorrowingAdapter;
import MODEL.Patterns.Iterator.BookIterator;
import MODEL.Patterns.Iterator.BorrowedBookCollection;

/**
 *
 * @author hussien
 */

public class ReservedState implements BookState {
    BookDAO bookDAO = new BookDAO();

/**
 * Represents the Reserved state of a book.
 * A book in this state has been reserved but is not yet checked out.
 */

    @Override
    public void handleNextAction(BookContext context, BorrowedBookCollection borrowedBooks) {
           try {
        // Iterate over the borrowed books collection
        BorrowDTO borrow = new BorrowDTO(0, context.getUserID(), 7);
        BorrowDAO borrowDAO = new BorrowDAO();
        borrowDAO.addBorrow(borrow);
        BookIterator iterator = borrowedBooks.createIterator();
        while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();
            if ("reserved".equals(bookDTO.getStatus())) {
                // Update the book's status to "checkedout"
                bookDTO.setStatus("checkedout");
                System.out.println("Book checked out successfully: " + bookDTO.getTitle());
                bookDAO.updateBook(bookDTO);

                BorrowDetailsDTO borrowDetails = new BorrowDetailsDTO(0, bookDTO.getId(), borrow.getId(), false);
                BorrowingDetailsDAO borrowDetailsDAO = new BorrowingDetailsDAO();
                borrowDetailsDAO.addBorrowingDetails(borrowDetails);

            }
        }

                BorrowingAdapter borrowAdapter = new BorrowingAdapter(borrow, borrowedBooks);  // Passing entire collection
                String filePath = "borrow_ticket.txt";
                borrowAdapter.saveTicketToFile(filePath);

                // Update the state to CheckedOut
                context.setState(new CheckedOutState());
    } catch (Exception e) {
        System.out.println("Error checking out book: " + e);
    }
    }

    @Override
    public void handlePreviousAction(BookContext context, BorrowedBookCollection borrowedBooks) {
        try {
            // If the user wants to cancel the reservation, revert to AvailableState
            BookIterator iterator = borrowedBooks.createIterator();
            while (iterator.hasNext()) {
                BookDTO bookDTO = iterator.next();
                if ("reserved".equals(bookDTO.getStatus())) {
                    // Revert the book's status to "available"
                    bookDTO.setStatus("requested");
                    bookDAO.updateBook(bookDTO);
                    System.out.println("Reservation for book canceled: " + bookDTO.getTitle());
                }
            }

            // Transition back to AvailableState after canceling reservation
            context.setState(new AvailableState());
        } catch (Exception e) {
            System.out.println("Error canceling reservation: " + e.getMessage());
        }
    }

    // Additional methods (reserveBook, requestBook, etc.) can be defined here as needed
}

