/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Iterator;

import MODEL.DTO.Book.BookDTO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hussien
 */
public class AvailableBookCollection implements BookCollection {
    
    private Map<Integer, BookDTO> books;
    public AvailableBookCollection(){
        books = new HashMap<>();
        
    }
    public void addBook(BookDTO book){
        books.put(book.getId(), book);
    
    }
    @Override
    public BookIterator createIterator() {
        return new AvailableBookIterator(this.books);
    }
    
}
