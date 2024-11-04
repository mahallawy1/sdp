/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.decorator;

/**
 *
 * @author mahallawy
 */
public class SupportUsDonation implements IDonation {
    private double amount;

    public SupportUsDonation(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getDescription() {
        return "Support Us Donation";
    }
}