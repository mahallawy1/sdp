/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.State;

import MODEL.DTO.Book.BookDTO;
import MODEL.Patterns.Iterator.BookIterator;
import MODEL.Patterns.Iterator.BorrowedBookCollection;
import View.UtilityHandler;
import java.util.Iterator;

/**
 *
 * @author hussien
 */





public class BookContext {
    private BookState state;
    private BorrowedBookCollection borrowedBooks;
    private Integer userID; // Store user ID
    public UtilityHandler UI;
    // Constructor updated to throw an exception if no books exist
    public BookContext(BorrowedBookCollection borrowedBooks, Integer userID) {
        this.borrowedBooks = borrowedBooks;
        this.userID = userID;
        this.UI = new UtilityHandler();
        // Check if borrowedBooks is empty
        BookIterator iterator = borrowedBooks.createIterator();
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Cannot construct BookContext with no books.");
        }

        // Set initial state using the first book's status
        BookDTO firstBook = iterator.next();
        this.state = BookStateFactory.getState(firstBook.getStatus());
    }
    public Integer getUserID(){
        return userID;
    }
    // Setter for updating the state
    public void setState(BookState state) {
        this.state = state;
    }

    // Executes the next state action
    public void executeNextState() {
        state.handleNextAction(this, borrowedBooks);
    }

    // Executes the previous state action
    public void executePrevState() {
        state.handlePreviousAction(this, borrowedBooks);
    }
}
