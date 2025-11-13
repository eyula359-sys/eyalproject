package com.erel.eyalproject.model;

public class Game {

    protected String id_game;

    protected String home_team;
    protected String away_team;
    protected String date;
    protected String hour;


    public Game(String away_team, String date, String home_team, String hour, String id_game) {
        this.away_team = away_team;
        this.date = date;
        this.home_team = home_team;
        this.hour = hour;
        this.id_game = id_game;
    }

    public Game() {
    }

    public String getAway_team() {
        return away_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getId_game() {
        return id_game;
    }

    public void setId_game(String id_game) {
        this.id_game = id_game;
    }

    @Override
    public String toString() {
        return "Game{" +
                "away_team='" + away_team + '\'' +
                ", id_game='" + id_game + '\'' +
                ", home_team='" + home_team + '\'' +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                '}';
    }
}
