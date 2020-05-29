package com.springlearning.mvc;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private float shippingCost;
    private float price;
    private Date orderDate;
    private long numberOfCopies;
    private String orderType;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Issue issue;
    private boolean paymentDone;
    @ManyToOne
    private Distributor distributor;

    public Order(){super();}

    public Order(float shippingCost, float price, Date orderDate, long numberOfCopies, boolean paymentDone, Distributor distributor, String orderType) {
        this.shippingCost = shippingCost;
        this.price = price;
        this.orderDate = orderDate;
        this.numberOfCopies = numberOfCopies;
        this.paymentDone = paymentDone;
        this.distributor = distributor;
        this.orderType = orderType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(float shippingCost) {
        this.shippingCost = shippingCost;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public long getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(long numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public boolean isPaymentDone() {
        return paymentDone;
    }

    public void setPaymentDone(boolean paymentDone) {
        this.paymentDone = paymentDone;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }
}

