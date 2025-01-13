/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.State;

import MODEL.DTO.Book.BookDTO;

/**
 *
 * @author hussien
 */
public class UnavailableState implements BookState {
    @Override
    public void requestBook(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is unavailable and cannot be requested.");
    }

    @Override
    public void reserveBook(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is unavailable and cannot be reserved.");
    }

    @Override
    public void checkoutBook(BookContext context,BookDTO book,int user_id) {
        System.out.println("Book is unavailable and cannot be checked out.");
    }

    @Override
    public void returnBook(BookContext context,BookDTO bookDTO) {
        System.out.println("Book cannot be returned as it is unavailable.");
    }

    @Override
    public void markOverdue(BookContext context,BookDTO bookDTO) {
        System.out.println("Book cannot be marked overdue as it is unavailable.");
    }

    @Override
    public void markUnavailable(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is already unavailable.");
    }

    @Override
    public void makeAvailable(BookContext context,BookDTO bookDTO) {
        System.out.println("Book is now available.");
        context.setState(new AvailableState());
    }
}