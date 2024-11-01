/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.MODEL.DTO;

/**
 *
 * @author mahallawy
 */
public class BorrowDTO {
    private int id;
    private Integer userId;
    private Integer days;

    public BorrowDTO(int id, Integer userId, Integer days) {
        this.id = id;
        this.userId = userId;
        this.days = days;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getDays() { return days; }
    public void setDays(Integer days) { this.days = days; }
    
}
