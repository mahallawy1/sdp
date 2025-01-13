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
public class OverdueState implements BookState {
    BookDAO bookDAO = new BookDAO();
    @Override
    public void requestBook(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is overdue and cannot be requested.");
    }

    @Override
    public void reserveBook(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is overdue and cannot be reserved.");
    }

    @Override
 public void checkoutBook(BookContext context,BookDTO book,int user_id) {
        System.out.println("Book is overdue and cannot be checked out.");
    }

    @Override
    public void returnBook(BookContext context,BookDTO bookDTO) {
        
        try{
            
            bookDTO.setStatus("returned");
            bookDAO.updateBook(bookDTO);
             System.out.println("Overdue book returned. Thank you.");
            context.setState(new ReturnedState());
            
        }catch(Exception e){
            System.out.println("Error returning book" + e);
        }
            
        
       
    }

    @Override
    public void markOverdue(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is already overdue.");
    }

    @Override
    public void markUnavailable(BookContext context,BookDTO bookDTO) {
        System.out.println("Book marked is already checked out.");
        
    }

    @Override
    public void makeAvailable(BookContext context,BookDTO bookDTO) {
        System.out.println("Book cannot be made available while overdue.");
    }
}