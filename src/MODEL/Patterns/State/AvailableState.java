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
    public void requestBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        try {
            BookIterator iterator = borrowedBooks.createIterator();
            while (iterator.hasNext()) {
                BookDTO book = iterator.next();
                book.setStatus("requested");
                System.out.println("Book requested: " + book.getTitle()); // Assuming `getTitle` is a method in `BookDTO`
                bookDAO.updateBook(book);
            }
            context.setState(new RequestedState());
        } catch (Exception e) {
            System.out.println("Error requesting books: " + e.getMessage());
        }
    }

    @Override
    public void reserveBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Books cannot be reserved directly. Request the books first.");
    }

    @Override
    public void checkoutBook(BookContext context, BorrowedBookCollection borrowedBooks, int user_id) {
        System.out.println("Books must be requested before checkout.");
    }

    @Override
    public void returnBook(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Books are already available.");
    }

    @Override
    public void markOverdue(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Books cannot be marked overdue while available.");
    }

    @Override
    public void markUnavailable(BookContext context, BorrowedBookCollection borrowedBooks) {
       try{
        BookIterator iterator = borrowedBooks.createIterator();
        while (iterator.hasNext()) {
            BookDTO book = iterator.next();
            System.out.println("Book marked as unavailable: " + book.getTitle());
            book.setStatus("unavailable");
            bookDAO.updateBook(book);
        }
        context.setState(new UnavailableState());
       }
       catch(Exception e){
        System.out.println("Error marking books as unavailable: " + e.getMessage());

       }
    }

    @Override
    public void makeAvailable(BookContext context, BorrowedBookCollection borrowedBooks) {
        System.out.println("Books are already available.");
    }
}