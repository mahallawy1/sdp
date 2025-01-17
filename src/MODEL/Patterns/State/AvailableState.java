/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.State;

import MODEL.DAO.BookDAO;

/**
 *
 * @author hussien
 */


import MODEL.DTO.Book.BookDTO;
import MODEL.Patterns.Iterator.BookIterator;
import MODEL.Patterns.Iterator.BorrowedBookCollection;
import java.util.Iterator;

public class AvailableState implements BookState {
    BookDAO bookDAO = new BookDAO();

       @Override
    public void handleNextAction(BookContext context, BorrowedBookCollection borrowedBooks) {
        try {
            BookIterator iterator = borrowedBooks.createIterator();
            while (iterator.hasNext()) {
                BookDTO book = iterator.next();
                book.setStatus("requested");
                System.out.println("Book requested: " + book.getTitle()); // Assuming `getTitle` is a method in `BookDTO`
                bookDAO.updateBook(book);
            }
            // Transition to RequestedState
            context.setState(new RequestedState());
        } catch (Exception e) {
            System.out.println("Error requesting books: " + e.getMessage());
        }
    }

    @Override
    public void handlePreviousAction(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Books are already in the available state; no previous state exists.");
    }
}