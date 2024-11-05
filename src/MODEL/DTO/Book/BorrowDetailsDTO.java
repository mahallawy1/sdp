/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DTO.Book;

/**
 *
 * @author mahallawy
 */
public class BorrowDetailsDTO {
    
    private int id;
    private int bookId;
    private int borrowId;
    private boolean returned;

    public BorrowDetailsDTO(int id, int bookId, int borrowId, boolean returned) {
        this.id = id;
        this.bookId = bookId;
        this.borrowId = borrowId;
        this.returned = returned;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getBorrowId() { return borrowId; }
    public void setBorrowId(int borrowId) { this.borrowId = borrowId; }

    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }
    
}
