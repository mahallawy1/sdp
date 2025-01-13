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
public class BookContext {
    private BookState state;
    private BookDTO bookDTO;
    public BookContext(BookDTO bookDTO) {
        if("available".equals(bookDTO.getStatus())){
            this.state = new AvailableState(); // Default state
        }else{
            this.state = new UnavailableState();
        }
        this.bookDTO = bookDTO;
    }

    public void setState(BookState state) {
        this.state = state;
    }

    public void requestBook() {
        state.requestBook(this,bookDTO);
    }

    public void reserveBook() {
        state.reserveBook(this,bookDTO);
    }

    public void checkoutBook(int user_id) {
        state.checkoutBook(this,bookDTO,user_id);
    }

    public void returnBook() {
        state.returnBook(this,bookDTO);
    }

    public void markOverdue() {
        state.markOverdue(this,bookDTO);
    }

    public void markUnavailable() {
        state.markUnavailable(this,bookDTO);
    }

    public void makeAvailable() {
        state.makeAvailable(this,bookDTO);
    }
}
