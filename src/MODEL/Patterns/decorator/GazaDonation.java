/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.decorator;

/**
 *
 * @author mahallawy
 */
public class GazaDonation extends DonationAddon {
    private double additionalAmount;

    public GazaDonation(IDonation donation, double additionalAmount) {
        super(donation);
        this.additionalAmount = additionalAmount;
    }

    @Override
    public double getAmount() {
        return super.getAmount() + additionalAmount;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Gaza Donation";
    }
}
