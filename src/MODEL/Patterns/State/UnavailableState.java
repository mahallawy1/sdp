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
    
public void handleNextAction(BookContext context, BorrowedBookCollection borrowedBooks) {
    try {
        // Iterate over the borrowed books collection
        BookIterator iterator = borrowedBooks.createIterator();
        while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();
            // Handle unavailable book actions
            if ("unavailable".equals(bookDTO.getStatus())) {
                context.UI.showMessage("book cannot be borrowed as it is unavailable : " + bookDTO.getTitle());
            }
        }
    } catch (Exception e) {
        context.UI.showMessage("Error requesting book on unavailable book: " + e.getMessage());
    }
}

   @Override
    public void handlePreviousAction(BookContext context, BorrowedBookCollection borrowedBooks) {
        // Keep the book in the unavailable state
        try {
            // Iterate over the borrowed books collection
            BookIterator iterator = borrowedBooks.createIterator();
            while (iterator.hasNext()) {
                BookDTO bookDTO = iterator.next();
                // Ensure the book stays unavailable
                if ("unavailable".equals(bookDTO.getStatus())) {
                    context.UI.showMessage("Book remains unavailable: " + bookDTO.getTitle());
                }
            }
        } catch (Exception e) {
            context.UI.showMessage("Error handling previous action on unavailable book: " + e.getMessage());
        }
    }
}