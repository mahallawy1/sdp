/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DTO.Book;

/**
 *
 * @author hussien
 */

public class BookDTO {
    private int id;
    private String description;
    private String title;
    private String cover;
    private Boolean deleted;
    private Integer publishYear;
    private Integer quantity;
    private String status;

    // Default constructor
    public BookDTO() {}

    // Constructor with all variables
    public BookDTO(int id, String description, String title, String cover, Boolean deleted, Integer publishYear, Integer quantity, String status) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.cover = cover;
        this.deleted = deleted;
        this.publishYear = publishYear;
        this.quantity = quantity;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCover() { return cover; }
    public void setCover(String cover) { this.cover = cover; }

    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

    public Integer getPublishYear() { return publishYear; }
    public void setPublishYear(Integer publishYear) { this.publishYear = publishYear; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}