/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.MODEL.DAO.Patterns.decorator;

/**
 *
 * @author mahallawy
 */
public class CharityDonation extends DonationAddon {
    private double additionalAmount;

    public CharityDonation(IDonation donation, double additionalAmount) {
        super(donation);
        this.additionalAmount = additionalAmount;
    }

    @Override
    public double getAmount() {
        return super.getAmount() + additionalAmount;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Charity Donation";
    }
}