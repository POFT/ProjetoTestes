package org.example.model;

public class Attraction {
    private int id;
    private String attractionName;
    private double priceAdult;
    private double priceChild;
    private int durationSeconds;
    private int ticketsSold;

    public Attraction(int id, String attractionName, double priceAdult, double priceChild, int durationSeconds) {
        this.id = id;
        this.attractionName = attractionName;
        this.priceAdult = priceAdult;
        this.priceChild = priceChild;
        this.durationSeconds = durationSeconds;
        this.ticketsSold = ticketsSold;
    }

    public int getId() {
        return id;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public double getPriceAdult() {
        return priceAdult;
    }

    public double getPriceChild() {
        return priceChild;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }
}
