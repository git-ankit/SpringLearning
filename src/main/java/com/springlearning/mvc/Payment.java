package com.springlearning.mvc;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date paymentDate;
    private float amount;

    @ManyToOne
    private Contributor paymentTo;

    public Payment(){super();}

    public Payment(Date paymentDate, float amount, Contributor paymentTo) {
        super();
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentTo = paymentTo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Contributor getPaymentTo() {
        return paymentTo;
    }

    public void setPaymentTo(Contributor paymentTo) {
        this.paymentTo = paymentTo;
    }
}
