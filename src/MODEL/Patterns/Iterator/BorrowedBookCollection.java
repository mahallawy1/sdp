/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Iterator;

import MODEL.DTO.Book.BookDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hussien
 */
public class BorrowedBookCollection implements BookCollection{

    private List<BookDTO> books ;
    public BorrowedBookCollection(){
        books = new ArrayList<>();
        
    }
    public void addBook(BookDTO e){
        this.books.add(e);
    }
    
    
    @Override
    public BookIterator createIterator() {
        return new BorrowedBookIterator(this.books);
    }
    
    
}
