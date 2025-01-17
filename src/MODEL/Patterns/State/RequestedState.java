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
    public void requestBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is already requested.");
    }

    @Override
    public void reserveBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        try {
            BookIterator iterator = borrowedBooks.createIterator();
            while (iterator.hasNext()) {
                BookDTO bookDTO = iterator.next();
                if ("requested".equals(bookDTO.getStatus())) {
                    bookDTO.setStatus("reserved");
                    bookDAO.updateBook(bookDTO);
                    System.out.println("Book reserved for the user: " + bookDTO.getTitle());
                    context.setState(new ReservedState());  // Transition to ReservedState
                }
            }
        } catch (Exception e) {
            System.out.println("Error reserving book: " + e);
        }
    }

    @Override
    public void checkoutBook(BookContext context, BorrowedBookCollection borrowedBooks, int user_id) {
        System.out.println("Wait for admin approval.");
        context.setState(new CheckedOutState());
    }

    @Override
    public void returnBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book cannot be returned. It has not been checked out yet.");
    }

    @Override
    public void markOverdue(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book cannot be marked overdue.");
    }

    @Override
    public void markUnavailable(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is requested, cannot be marked as unavailable.");
        context.setState(new UnavailableState());  // Transition to UnavailableState
    }

    @Override
    public void makeAvailable(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is already available.");
        context.setState(new AvailableState());  // Transition to AvailableState
    }
}