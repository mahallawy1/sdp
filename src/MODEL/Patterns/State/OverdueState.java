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

public class OverdueState implements BookState {
    BookDAO bookDAO = new BookDAO();

    @Override
    public void requestBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is overdue and cannot be requested.");
    }

    @Override
    public void reserveBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is overdue and cannot be reserved.");
    }

    @Override
    public void checkoutBook(BookContext context, BorrowedBookCollection borrowedBooks, int user_id) {
        System.out.println("Book is overdue and cannot be checked out.");
    }

    @Override
    public void returnBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        BookIterator iterator = borrowedBooks.createIterator();

        while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();
            if ("overdue".equals(bookDTO.getStatus())) {
                try {
                    bookDTO.setStatus("returned");
                    bookDAO.updateBook(bookDTO);
                    System.out.println("Overdue book returned: " + bookDTO.getTitle());
                    context.setState(new ReturnedState()); // Update to ReturnedState
                } catch (Exception e) {
                    System.out.println("Error returning overdue book: " + e);
                }
            }
        }
    }

    @Override
    public void markOverdue(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is already overdue.");
    }

    @Override
    public void markUnavailable(BookContext context, BorrowedBookCollection borrowedBooks) {
        BookIterator iterator = borrowedBooks.createIterator();

        while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();
            if ("overdue".equals(bookDTO.getStatus())) {
                System.out.println("Book is already overdue and cannot be marked as unavailable.");
            }
        }
    }

    @Override
    public void makeAvailable(BookContext context, BorrowedBookCollection borrowedBooks) {
        BookIterator iterator = borrowedBooks.createIterator();

        while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();
            if ("overdue".equals(bookDTO.getStatus())) {
                System.out.println("Book cannot be made available while overdue.");
            }
        }
    }
}