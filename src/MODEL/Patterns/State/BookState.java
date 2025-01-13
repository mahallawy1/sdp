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
public interface BookState {
    void requestBook(BookContext context,BookDTO book);
    void reserveBook(BookContext context,BookDTO book);
    void checkoutBook(BookContext context,BookDTO book,int user_id);
    void returnBook(BookContext context,BookDTO book);
    void markOverdue(BookContext context,BookDTO book);
    void markUnavailable(BookContext context,BookDTO book);
    void makeAvailable(BookContext context,BookDTO book);
}
