/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DAO.Patterns.decorator;

/**
 *
 * @author mahallawy
 */
public abstract class DonationAddon implements IDonation {
    protected IDonation donation;

    public DonationAddon(IDonation donation) {
        this.donation = donation;
    }

    @Override
    public double getAmount() {
        return donation.getAmount();
    }

    @Override
    public String getDescription() {
        return donation.getDescription();
    }
}
