package com.erel.eyalproject.model;

public class Ticket {

    protected String ticketId;

    protected Game game;
    protected double price;
    protected String section;
    protected String row;
    protected String seat;
    protected String currency;
    protected boolean is_available;

    protected User user;

    public Ticket(String currency, Game game, boolean is_available, double price, String row, String seat, String section, String ticketId, User user) {
        this.currency = currency;
        this.game = game;
        this.is_available = is_available;
        this.price = price;
        this.row = row;
        this.seat = seat;
        this.section = section;
        this.ticketId = ticketId;
        this.user = user;
    }

    public Ticket() {
    }

    public Ticket(String ticketId, Game selectedGame, double price, String section, String strow, String stseat, String currency, boolean b, User currentUser) {

        this.currency = currency;
        this.game = selectedGame;
        this.is_available = b;
        this.price = price;
        this.row = strow;
        this.seat = stseat;
        this.section = section;
        this.ticketId = ticketId;
        this.user = currentUser;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
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
                "currency='" + currency + '\'' +
                ", id='" + ticketId + '\'' +
                ", game=" + game +
                ", price=" + price +
                ", section='" + section + '\'' +
                ", row=" + row +
                ", seat=" + seat +
                ", is_available=" + is_available +
                ", user=" + user +
                '}';
    }
}
