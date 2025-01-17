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
    public void handleNextAction(BookContext context, BorrowedBookCollection borrowedBooks) {
        try {
            // In the checked out state, the action might be to return the book or mark it overdue
            BookIterator iterator = borrowedBooks.createIterator();
            while (iterator.hasNext()) {
                BookDTO bookDTO = iterator.next();
                if ("checkedout".equals(bookDTO.getStatus())) {
                    // Check if the book is being returned
                    bookDTO.setStatus("overdue");
                    bookDAO.updateBook(bookDTO);
                    System.out.println("Book marked as overdue: " + bookDTO.getTitle());

                    // Transition to ReturnedState after returning the book
                    context.setState(new OverdueState());
                }
            }
        } catch (Exception e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }
    
    @Override
    public void handlePreviousAction(BookContext context, BorrowedBookCollection borrowedBooks) {
        try {
            // The previous action could involve canceling the checkout and marking the book as reserved
            BookIterator iterator = borrowedBooks.createIterator();
            while (iterator.hasNext()) {
                BookDTO bookDTO = iterator.next();
                if ("checkedout".equals(bookDTO.getStatus())) {
                    // Change the book status to "reserved" (go back to reserved state)
                    bookDTO.setStatus("reserved");
                    bookDAO.updateBook(bookDTO);
                    System.out.println("Book status changed back to reserved: " + bookDTO.getTitle());

                    // Transition to ReservedState
                    context.setState(new ReservedState());
                }
            }
        } catch (Exception e) {
            System.out.println("Error marking book as reserved: " + e.getMessage());
        }
    }

   
}