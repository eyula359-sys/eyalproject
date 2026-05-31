package com.erel.eyalproject.model;

public class Order {

    protected String id;
    protected Ticket ticket;
    protected User buyer;
    protected Integer price;
    protected String date;

    protected  String status;

    public Order(String id,User buyer, String date,  Integer price, Ticket ticket) {
        this.buyer = buyer;
        this.date = date;
        this.id = id;
        this.price = price;
        this.ticket = ticket;
        this.status = "new";

    }

    public Order(String id, User buyer, String date, Integer price, Ticket ticket, String status) {
        this.buyer = buyer;
        this.date = date;
        this.id = id;
        this.price = price;
        this.status = status;
        this.ticket = ticket;
    }

    public Order() {
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "buyer=" + buyer +
                ", id='" + id + '\'' +
                ", ticket=" + ticket +
                ", price=" + price +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
