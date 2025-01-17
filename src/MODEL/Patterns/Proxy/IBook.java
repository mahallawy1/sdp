/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MODEL.Patterns.Proxy;

/**
 *
 * @author Eslam
 */
public interface IBook {
    void display();
    void update(String title, Integer publishYear,String description,Integer quantity);
    // Add other relevant methods
}