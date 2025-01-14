/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MODEL.Patterns.State;

import MODEL.DTO.Book.BookDTO;

/**
 *
 * @author hussien
 */


import MODEL.DTO.Book.BookDTO;
import MODEL.Patterns.Iterator.BorrowedBookCollection;

public interface BookState {
    void requestBook(BookContext context, BorrowedBookCollection borrowedBooks);
    void reserveBook(BookContext context, BorrowedBookCollection borrowedBooks);
    void checkoutBook(BookContext context, BorrowedBookCollection borrowedBooks, int user_id);
    void returnBook(BookContext context, BorrowedBookCollection borrowedBooks);
    void markOverdue(BookContext context, BorrowedBookCollection borrowedBooks);
    void markUnavailable(BookContext context, BorrowedBookCollection borrowedBooks);
    void makeAvailable(BookContext context, BorrowedBookCollection borrowedBooks);
}