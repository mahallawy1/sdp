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
public class ReturnedState implements BookState {
    BookDAO bookDAO = new BookDAO();
    @Override
    public void requestBook(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is now available for request.");
        context.setState(new RequestedState());
    }

    @Override
    public void reserveBook(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is now available for reservation.");
        context.setState(new ReservedState());
    }

    @Override
 public void checkoutBook(BookContext context,BookDTO book,int user_id) {
        System.out.println("Book must be requested or reserved before checkout.");
    }

    @Override
    public void returnBook(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is already returned.");
    }

    @Override
    public void markOverdue(BookContext context,BookDTO bookDTO) {
        System.out.println("Book cannot be marked overdue after being returned.");
    }

    @Override
    public void markUnavailable(BookContext context,BookDTO bookDTO) {
        System.out.println("Book has just returned.");
    }

    @Override
    public void makeAvailable(BookContext context,BookDTO bookDTO) {
       try{
        bookDTO.setStatus("available");
        bookDAO.updateBook(bookDTO);
        System.out.println("Book is now available.");
        context.setState(new AvailableState());
       }
       catch(Exception e){
           System.out.println("Error making book available" + e);
       }
       
    }
}