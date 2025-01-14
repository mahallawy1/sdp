/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Iterator;

import MODEL.DTO.Book.BookDTO;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *
 * @author hussien
 */
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class AvailableBookIterator implements BookIterator {
    private final Map<Integer, BookDTO> books;
    private final Iterator<Map.Entry<Integer, BookDTO>> iterator;

    // Constructor initializes the books map and its iterator
    AvailableBookIterator(Map<Integer, BookDTO> books) {
        this.books = books; // Assign the provided map to the class field
        this.iterator = books.entrySet().iterator(); // Initialize the iterator
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext(); // Delegate to the iterator's hasNext
    }

    @Override
    public BookDTO next() {
        if (hasNext()) {
            return iterator.next().getValue(); // Return the BookDTO value of the current entry
        }
        throw new NoSuchElementException(); // Throw exception if no more elements
    }
}