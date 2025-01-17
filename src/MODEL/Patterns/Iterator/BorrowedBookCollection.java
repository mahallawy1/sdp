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
import java.util.Set;
import java.util.HashSet;

public class BorrowedBookCollection implements BookCollection {

    private Set<BookDTO> books;

    public BorrowedBookCollection() {
        books = new HashSet<>();
    }

    public void addBook(BookDTO e) {
        this.books.add(e);
    }

    @Override
    public BookIterator createIterator() {
        return new BorrowedBookIterator(this.books);
    }
}
