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

    @Override
    public void requestBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is already reserved. Cannot request again.");
    }

    @Override
    public void reserveBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is already reserved.");
    }

    public void checkoutBook(BookContext context, BorrowedBookCollection borrowedBooks, int user_id) {
    try {
        // Iterate over the borrowed books collection
        BorrowDTO borrow = new BorrowDTO(0, user_id, 7);
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
    public void returnBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book cannot be returned without being checked out.");
    }

    @Override
    public void markOverdue(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book cannot be marked overdue without being checked out.");
    }

    @Override
    public void markUnavailable(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book marked as unavailable.");
        context.setState(new UnavailableState());
    }

    @Override
    public void makeAvailable(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Reservation canceled. Book is now available.");
        context.setState(new AvailableState());
    }
}