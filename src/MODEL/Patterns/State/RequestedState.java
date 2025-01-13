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
public class RequestedState implements BookState {
    BookDAO bookDAO = new BookDAO();

    @Override
    public void requestBook(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is already requested.");
    }

    @Override
    public void reserveBook(BookContext context,BookDTO bookDTO) {
        try{
            bookDTO.setStatus("reserved");
        
            System.out.println("Book reserved for the user.");
            bookDAO.updateBook(bookDTO);
            context.setState(new ReservedState());
        }
        catch(Exception e){
            System.out.println("Error reserving book" +e);
        }
    }

    @Override
   public void checkoutBook(BookContext context,BookDTO book,int user_id) {
        System.out.println("Wait for admin approval");
        context.setState(new CheckedOutState());
    }

    @Override
    public void returnBook(BookContext context,BookDTO bookDTO) {
        System.out.println("Book cannot be returned. It has not been checked out yet.");
    }

    @Override
    public void markOverdue(BookContext context,BookDTO bookDTO) {
        System.out.println("Book cannot be marked overdue.");
    }

    @Override
    public void markUnavailable(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is requested, can not be marked as unavailable");
        context.setState(new UnavailableState());
    }

    @Override
    public void makeAvailable(BookContext context,BookDTO bookDTO) {
        System.out.println("book is already available.");
        context.setState(new AvailableState());
    }
}
