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
public class BorrowedBookIterator implements BookIterator {
    private List<BookDTO> books = new ArrayList<>();
    private int position = 0;
    
    BorrowedBookIterator(List<BookDTO> books){
        this.books = books;
        
    }
    
    @Override
    public boolean hasNext() {
        return position < books.size();
    }

    @Override
    public BookDTO next() {
       
       return books.get(position++);
      
    }
    
    
}
