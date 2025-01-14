/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.State;

import MODEL.DTO.Book.BookDTO;
import MODEL.Patterns.Iterator.BookIterator;
import MODEL.Patterns.Iterator.BorrowedBookCollection;
import java.util.Iterator;

/**
 *
 * @author hussien
 */


public class BookContext {
    private BookState state;
    private BorrowedBookCollection borrowedBooks; // Holds BorrowedBookCollection

    // Constructor accepts BorrowedBookCollection
    public BookContext(BorrowedBookCollection borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
        
        // Default state for the first book (if the collection is not empty)
        BookIterator iterator = borrowedBooks.createIterator();
        if (iterator.hasNext()) {
            BookDTO firstBook = iterator.next();
            String initialState = firstBook.getStatus();
            switch(initialState){
                case "available":  this.state = new AvailableState(); 
                                   break;
                case "unavailable":  this.state = new UnavailableState(); 
                                   break;
                case "requested":  this.state = new RequestedState(); 
                                   break;
                case "reserved":  this.state = new ReservedState(); 
                                   break;
                case "checkedout":  this.state = new CheckedOutState(); 
                                   break;
                case "overdue":  this.state = new OverdueState(); 
                                   break;
                case "returned":  this.state = new ReturnedState(); 
                                   break;
 
                
            
        }
    }
    }
    // Setter to update the state of the books
    public void setState(BookState state) {
        this.state = state;
    }

    // Perform actions on the entire borrowed books collection
    public void requestBooks() {
        state.requestBook(this, borrowedBooks);  // Pass the entire BorrowedBookCollection to the state method
    }

    public void reserveBooks() {
        state.reserveBook(this, borrowedBooks);  // Pass the entire BorrowedBookCollection to the state method
    }

    public void checkoutBooks(int user_id) {
        state.checkoutBook(this, borrowedBooks, user_id);  // Pass the entire BorrowedBookCollection to the state method
    }

    public void returnBooks() {
        state.returnBook(this, borrowedBooks);  // Pass the entire BorrowedBookCollection to the state method
    }

    public void markOverdueBooks() {
        state.markOverdue(this, borrowedBooks);  // Pass the entire BorrowedBookCollection to the state method
    }

    public void markUnavailableBooks() {
        state.markUnavailable(this, borrowedBooks);  // Pass the entire BorrowedBookCollection to the state method
    }

    public void makeBooksAvailable() {
        state.makeAvailable(this, borrowedBooks);  // Pass the entire BorrowedBookCollection to the state method
    }
}