/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MODEL.Patterns.Iterator;

import MODEL.DTO.Book.BookDTO;

/**
 *
 * @author hussien
 */
public interface BookIterator {
    
    boolean hasNext();
    BookDTO next();
    
}
