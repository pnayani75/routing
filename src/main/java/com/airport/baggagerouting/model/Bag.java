package com.airport.baggagerouting.model;

public class Bag {
    private String bagId;
    private String bagEntryGate;
    private String flight;

    public Bag(String bagId, String bagEntryGate, String flight) {
        this.bagId = bagId;
        this.bagEntryGate = bagEntryGate;
        this.flight = flight;
    }

    public String getBagId() {
        return bagId;
    }

    public void setBagId(String bagId) {
        this.bagId = bagId;
    }

    public String getBagEntryGate() {
        return bagEntryGate;
    }

    public void setBagEntryGate(String bagEntryGate) {
        this.bagEntryGate = bagEntryGate;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }
}
