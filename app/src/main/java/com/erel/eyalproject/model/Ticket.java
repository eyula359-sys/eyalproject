package com.erel.eyalproject.model;

public class Ticket {

    protected String id;

    protected Game game;
    protected double price;
    protected String section;
    protected int row;
    protected int seat;
    protected char currency;
    protected boolean is_available;

    protected User user;

    public Ticket(String id, Game game, double price, String section, int row, int seat, char currency, boolean is_available, User user) {
        this.id = id;

        this.game = game;
        this.price = price;
        this.section = section;
        this.row = row;
        this.seat = seat;
        this.currency = currency;
        this.is_available = is_available;
        this.user = user;
    }

    public Ticket() {
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    public Integer getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public char getCurrency() {
        return currency;
    }

    public void setCurrency(char currency) {
        this.currency = currency;
    }

    public Boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(Boolean is_available) {
        this.is_available = is_available;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                ", id='" + id + '\'' +
                ", game=" + game +
                ", price=" + price +
                ", section='" + section + '\'' +
                ", row=" + row +
                ", seat=" + seat +
                "currency=" + currency +
                ", is_available=" + is_available +

                ", user=" + user +
                '}';
    }
}
