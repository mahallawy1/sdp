/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL.DTO.Donation;

import java.time.LocalDateTime;

/**
 *
 * @author mahallawy
 */
public class PaymentDTO {
    private int id;
    private Integer paymentMethodId;
    private LocalDateTime timestamp;
    private Boolean isDeleted;

    public PaymentDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Integer getPaymentMethodId() { return paymentMethodId; }
    public void setPaymentMethodId(Integer paymentMethodId) { this.paymentMethodId = paymentMethodId; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Boolean getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
}