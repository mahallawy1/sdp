/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.State;

import MODEL.DTO.Book.BookDTO;
import MODEL.Patterns.Iterator.BookIterator;
import MODEL.Patterns.Iterator.BorrowedBookCollection;

/**
 *
 * @author hussien
 */


public class UnavailableState implements BookState {
    
    @Override
    public void requestBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is unavailable and cannot be requested.");
    }

    @Override
    public void reserveBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is unavailable and cannot be reserved.");
    }

    @Override
    public void checkoutBook(BookContext context, BorrowedBookCollection borrowedBooks, int user_id) {
        System.out.println("Book is unavailable and cannot be checked out.");
    }

    @Override
    public void returnBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book cannot be returned as it is unavailable.");
    }

    @Override
    public void markOverdue(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book cannot be marked overdue as it is unavailable.");
    }

    @Override
    public void markUnavailable(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is already unavailable.");
    }

    public void makeAvailable(BookContext context, BorrowedBookCollection borrowedBooks) {
        // Use the BookIterator to iterate over the BorrowedBookCollection
        BookIterator iterator = borrowedBooks.createIterator();

        // Iterate over each book in the collection
        while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();

            try {
                bookDTO.setStatus("available");
                System.out.println("Book is now available: " + bookDTO.getTitle());
                // Update the book status in the database (if applicable)
                // bookDAO.updateBook(bookDTO);
            } catch (Exception e) {
                System.out.println("Error making book available: " + e);
            }
        }

        // After updating the status of all books, transition to AvailableState
        context.setState(new AvailableState());
    }
}