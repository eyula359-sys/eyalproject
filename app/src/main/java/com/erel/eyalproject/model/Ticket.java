package com.erel.eyalproject.model;

public class Ticket {

    protected String ticketId;
    protected Game game;
    protected double price;
    protected String section;
    protected String row;
    protected String seat;
    protected int quantity;
    protected boolean is_available;
    protected User user;

    public Ticket(String ticketId, Game game, double price, String section, String row, String seat, int quantity, boolean is_available, User user) {
        this.ticketId = ticketId;
        this.game = game;
        this.price = price;
        this.section = section;
        this.row = row;
        this.seat = seat;
        this.quantity = quantity;
        this.is_available = is_available;
        this.user = user;
    }

    public Ticket() {
    }

    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public String getRow() { return row; }
    public void setRow(String row) { this.row = row; }

    public String getSeat() { return seat; }
    public void setSeat(String seat) { this.seat = seat; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public boolean getIs_available() { return is_available; }
    public void setIs_available(boolean is_available) { this.is_available = is_available; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + ticketId + '\'' +
                ", game=" + game +
                ", price=" + price +
                ", section='" + section + '\'' +
                ", row=" + row +
                ", seat=" + seat +
                ", quantity=" + quantity +
                ", is_available=" + is_available +
                ", user=" + user +
                '}';
    }
}