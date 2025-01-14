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

public class CheckedOutState implements BookState {
    BookDAO bookDAO = new BookDAO();

    @Override
    public void requestBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is already checked out. Cannot request.");
    }

    @Override
    public void reserveBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Book is already checked out. Cannot reserve.");
    }

    @Override
    public void checkoutBook(BookContext context, BorrowedBookCollection borrowedBooks, int user_id) {
        System.out.println("Book is already checked out.");
    }

    @Override
    public void returnBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        BookIterator iterator = borrowedBooks.createIterator();
        while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();
            if ("checked out".equals(bookDTO.getStatus())) {
                try {
                    bookDTO.setStatus("returned");
                    bookDAO.updateBook(bookDTO);
                    System.out.println("Book returned successfully: " + bookDTO.getTitle());
                    context.setState(new ReturnedState()); // Update state to Returned
                } catch (Exception e) {
                    System.out.println("Error returning book: " + e);
                }
            }
        }
    }

    @Override
    public void markOverdue(BookContext context, BorrowedBookCollection borrowedBooks) {
      BookIterator iterator = borrowedBooks.createIterator();
      while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();
            if ("checked out".equals(bookDTO.getStatus())) {
                try {
                    bookDTO.setStatus("overdue");
                    bookDAO.updateBook(bookDTO);
                    System.out.println("Book is now overdue: " + bookDTO.getTitle());
                    context.setState(new OverdueState()); // Update state to Overdue
                } catch (Exception e) {
                    System.out.println("Error marking book as overdue: " + e);
                }
            }
        }
    }

    @Override
    public void markUnavailable(BookContext context, BorrowedBookCollection borrowedBooks) {
      BookIterator iterator = borrowedBooks.createIterator();    
      while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();
            if ("checked out".equals(bookDTO.getStatus())) {
                System.out.println("Book is already checked out. Cannot mark as unavailable.");
            }
        }
    }

    @Override
    public void makeAvailable(BookContext context, BorrowedBookCollection borrowedBooks) {
      BookIterator iterator = borrowedBooks.createIterator();
      while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();
            if ("checked out".equals(bookDTO.getStatus())) {
                System.out.println("Book is already checked out. Cannot make it available.");
            }
        }
    }
}