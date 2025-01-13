/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.State;

import MODEL.DAO.BookDAO;
import MODEL.DTO.Book.BookDTO;

/**
 *
 * @author hussien
 */
public class AvailableState implements BookState {
    BookDAO bookDAO = new BookDAO();

    @Override
    public void requestBook(BookContext context,BookDTO book) {
       try{
        book.setStatus("requested");
        System.out.println("Book requested.");
        bookDAO.updateBook(book);
        context.setState(new RequestedState());
       }
       catch(Exception e){
           System.out.println("Error requesting book"+e);
       }
       }

    @Override
    public void reserveBook(BookContext context,BookDTO book) {
        System.out.println("Book cannot be reserved directly. Request the book first.");
    }

    @Override
    public void checkoutBook(BookContext context,BookDTO book,int user_id) {
        System.out.println("Book must be requested before checkout.");
    }

    @Override
    public void returnBook(BookContext context,BookDTO book) {
        System.out.println("Book is already available.");
    }

    @Override
    public void markOverdue(BookContext context,BookDTO book) {
        System.out.println("Book cannot be marked overdue.");
    }

    @Override
    public void markUnavailable(BookContext context,BookDTO book) {
        System.out.println("Book marked as unavailable.");
        context.setState(new UnavailableState());
    }

    @Override
    public void makeAvailable(BookContext context,BookDTO book) {
        System.out.println("Book is already available.");
    }
}
