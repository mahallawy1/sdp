/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DTO.Event;

/**
 *
 * @author mahallawy
 */
public class VolunteeringDTO {
    private int id;
    
    private Integer userId;
    
    public VolunteeringDTO(int userId){
    this.userId=userId;}
    public VolunteeringDTO(int id,int userId) {
        this.id = id;
       
        this.userId = userId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
}
