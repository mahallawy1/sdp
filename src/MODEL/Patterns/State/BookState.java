/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MODEL.Patterns.State;

import MODEL.DTO.Book.BookDTO;
import MODEL.Patterns.Iterator.BorrowedBookCollection;

/**
 *
 * @author hussien
 */


public interface BookState {
   
    void handleNextAction(BookContext context, BorrowedBookCollection borrowedBooks);
    void handlePreviousAction(BookContext context, BorrowedBookCollection borrowedBooks);
}
