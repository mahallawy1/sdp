/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Iterator;

import MODEL.DTO.Book.BookDTO;

/**
 *
 * @author hussien
 */
import java.util.Iterator;
import java.util.Set;

public class BorrowedBookIterator implements BookIterator {
    private Iterator<BookDTO> iterator; // Use an Iterator to traverse the Set
    
    public BorrowedBookIterator(Set<BookDTO> books) {
        this.iterator = books.iterator(); // Initialize the iterator for the Set
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext(); // Check if more elements are available
    }

    @Override
    public BookDTO next() {
        return iterator.next(); // Retrieve the next element
    }
}
