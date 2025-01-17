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
    public void handleNextAction(BookContext context, BorrowedBookCollection borrowedBooks) {
        BookIterator iterator = borrowedBooks.createIterator();

        while (iterator.hasNext()) {
            BookDTO bookDTO = iterator.next();
            if ("overdue".equals(bookDTO.getStatus())) {
                try {
                    bookDTO.setStatus("returned");
                    bookDAO.updateBook(bookDTO);
                    context.UI.showMessage("Overdue book returned: " + bookDTO.getTitle());
                    context.setState(new ReturnedState()); 
                } catch (Exception e) {
                    context.UI.showMessage("Error returning overdue book: " + e);
                }
            }
        }
    }
      @Override
    public void handlePreviousAction(BookContext context, BorrowedBookCollection borrowedBooks) {
        try {
            
            BookIterator iterator = borrowedBooks.createIterator();
            while (iterator.hasNext()) {
                BookDTO bookDTO = iterator.next();
                if ("overdue".equals(bookDTO.getStatus())) {
                  
                    bookDTO.setStatus("overdue");
                    bookDAO.updateBook(bookDTO);
                    context.UI.showMessage("Book is now overdue: " + bookDTO.getTitle());

                    
                    context.setState(new OverdueState());
                }
            }
        } catch (Exception e) {
            context.UI.showMessage("Error marking book as overdue: " + e.getMessage());
        }
    }

}