/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author hussien
 */


import java.util.Scanner;

public class DonationView {
    private Scanner scanner;

    public DonationView() {
        this.scanner = new Scanner(System.in);
    }

    public boolean confirmDonation(String message) {
        System.out.print(message);
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }
     public boolean confirm(String message) {
      System.out.print(message);
       return scanner.nextLine().trim().equalsIgnoreCase("y");
    }
    public double getDonationAmount(String message) {
        System.out.print(message);
        return scanner.nextDouble();
    }

    public int getPaymentChoice() {
        System.out.println("Choose payment method:");
        System.out.println("1. Fawry Payment");
        System.out.println("2. Credit Card Payment");
        System.out.print("Enter choice: ");
        return scanner.nextInt();
    }
}
