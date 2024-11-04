/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DTO;

/**
 *
 * @author mahallawy
 */
public class AuthorsIdDTO {
      private int id;
    private Integer authorId;
    private Integer bookId;

    public AuthorsIdDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Integer getAuthorId() { return authorId; }
    public void setAuthorId(Integer authorId) { this.authorId = authorId; }

    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
}
