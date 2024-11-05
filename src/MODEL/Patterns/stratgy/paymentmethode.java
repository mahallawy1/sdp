/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.Patterns.stratgy;

/**
 *
 * @author belal
 */
public class paymentmethode {
    public IPayments ref;
    private int id;
    private String name;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public void choosepayment(IPayments X){
        ref=X;
    }
    public void pay(){
        ref.paymentmethode("payment");
    }
    
   // public static void main(String[] args) {
        
      //  paymentmethode pay = new paymentmethode();
      //  pay.choosepayment(new FawryPayment());
      //  pay.pay();
    
   // }
        
    
}
