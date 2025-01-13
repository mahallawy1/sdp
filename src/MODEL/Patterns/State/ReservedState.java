/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.State;

import MODEL.DAO.BookDAO;
import MODEL.DTO.Book.BookDTO;
import MODEL.DTO.Book.BorrowDTO;
import MODEL.DTO.Book.BorrowDetailsDTO;
import MODEL.Patterns.Adabter.BorrowingAdapter;

/**
 *
 * @author hussien
 */
public class ReservedState implements BookState {
    BookDAO bookDAO = new BookDAO();
    @Override
    public void requestBook(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is already reserved. Cannot request again.");
    }

    @Override
    public void reserveBook(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is already reserved.");
    }

    @Override
    public void checkoutBook(BookContext context,BookDTO bookDTO,int user_id) {
       try{
        bookDTO.setStatus("checkedout");
        System.out.println("Book checked out successfully.");
        bookDAO.updateBook(bookDTO);
        BorrowDTO borrow = new BorrowDTO(0,user_id,7);
        BorrowDetailsDTO borrowDetails = new BorrowDetailsDTO(0,bookDTO.getId(),borrow.getId(),false);
        
        BorrowingAdapter borrow_adapter = new BorrowingAdapter(borrow,bookDTO);
        String filePath = "borrow_ticket.txt";
        borrow_adapter.saveTicketToFile(filePath);
        //make a ticket for borrowing book
        context.setState(new CheckedOutState());
       }
       catch(Exception e){
           System.out.println("Error checking out book"+e);
       }
       }

    @Override
    public void returnBook(BookContext context,BookDTO bookDTO) {
        System.out.println("Book cannot be returned without being checked out.");
    }

    @Override
    public void markOverdue(BookContext context,BookDTO bookDTO) {
        System.out.println("Book cannot be marked overdue without being checked out.");
    }

    @Override
    public void markUnavailable(BookContext context,BookDTO bookDTO) {
        System.out.println("Book marked as unavailable.");
        context.setState(new UnavailableState());
    }

    @Override
    public void makeAvailable(BookContext context,BookDTO bookDTO) {
        System.out.println("Reservation canceled. Book is now available.");
        context.setState(new AvailableState());
    }
}
