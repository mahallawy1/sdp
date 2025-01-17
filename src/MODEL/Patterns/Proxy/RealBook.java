/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.Proxy;

import View.UtilityHandler;

/**
 *
 * @author Eslam
 */
// Real Book class
public class RealBook implements IBook {
    private String title;
    private String author;
    private double price;
    private UtilityHandler UI;
    public RealBook(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.UI  = new UtilityHandler();
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
        UI.showMessage("Book: " + title + " by " + author + ", Price: $" + price);
    }
    
    @Override
    public void update(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
