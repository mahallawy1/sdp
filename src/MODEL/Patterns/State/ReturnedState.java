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
    public void requestBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        // Iterate over the collection of books to handle their individual states
        BookIterator iterator = borrowedBooks.createIterator();
        while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();
            System.out.println("Book is now available for request: " + bookDTO.getTitle());
            context.setState(new RequestedState());  // Change to RequestedState for each book
        }
    }

    @Override
    public void reserveBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        // Iterate over the collection of books to handle their individual states
        BookIterator iterator = borrowedBooks.createIterator();
        while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();
            System.out.println("Book is now available for reservation: " + bookDTO.getTitle());
            context.setState(new ReservedState());  // Change to ReservedState for each book
        }
    }

    @Override
    public void checkoutBook(BookContext context, BorrowedBookCollection borrowedBooks, int user_id) {
        System.out.println("Book must be requested or reserved before checkout.");
    }

    @Override
    public void returnBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is already returned.");
    }

    @Override
    public void markOverdue(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book cannot be marked overdue after being returned.");
    }

    @Override
    public void markUnavailable(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book has just been returned.");
    }

    @Override
    public void makeAvailable(BookContext context, BorrowedBookCollection borrowedBooks) {
        try {
            // Iterate over the borrowed books collection to update the status of each book
            BookIterator iterator = borrowedBooks.createIterator();
            while (iterator.hasNext()) {
                BookDTO bookDTO = iterator.next();
                // Update the status of the book to available
                bookDTO.setStatus("available");
                bookDAO.updateBook(bookDTO);  // Update book in the database
                System.out.println("Book is now available: " + bookDTO.getTitle());
            }
            // After making all books available, change the state
            context.setState(new AvailableState());  // Transition to AvailableState after updating
        } catch (Exception e) {
            System.out.println("Error making books available: " + e.getMessage());
        }
    }
}