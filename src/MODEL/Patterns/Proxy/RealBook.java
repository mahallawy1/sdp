/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Proxy;

/**
 *
 * @author Eslam
 */
// Real Book class
public class RealBook implements IBook {
    private String title;
    private String author;
    private double price;
    
    public RealBook(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
        // Simulating heavy loading
        loadFromDatabase();
    }
    
    private void loadFromDatabase() {
        // Simulate loading from database
        try {
            Thread.sleep(1000); // Simulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void display() {
        System.out.println("Book: " + title + " by " + author + ", Price: $" + price);
    }
    
    @Override
    public void update(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
