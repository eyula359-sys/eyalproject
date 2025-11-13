package com.erel.eyalproject.model;

public class Ticket {

    protected String ticket_id;

    protected Game game;
    protected Integer price;
    protected String section;
    protected Integer row;
    protected Integer seat;
    protected char currency;
    protected boolean is_available;
    protected String ticket_type;

     protected User user;

    public Ticket(char currency, Game game, boolean is_available, Integer price, Integer row, Integer seat, String section, String ticket_id, String ticket_type, User user) {
        this.currency = currency;
        this.game = game;
        this.is_available = is_available;
        this.price = price;
        this.row = row;
        this.seat = seat;
        this.section = section;
        this.ticket_id = ticket_id;
        this.ticket_type = ticket_type;
        this.user = user;
    }



    public Ticket() {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
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
                "currency=" + currency +
                ", ticket_id='" + ticket_id + '\'' +
                ", game=" + game +
                ", price=" + price +
                ", section='" + section + '\'' +
                ", row=" + row +
                ", seat=" + seat +
                ", is_available=" + is_available +
                ", ticket_type='" + ticket_type + '\'' +
                ", user=" + user +
                '}';
    }
}
