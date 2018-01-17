package com.airport.baggagerouting.model;

public class TerminalConnection {

    private Terminal startTerminal;
    private Terminal endTerminal;
    private int distance;

    public TerminalConnection(Terminal startTerminal, Terminal endTerminal, int distance) {
        this.startTerminal = startTerminal;
        this.endTerminal = endTerminal;
        this.distance = distance;
    }
    public TerminalConnection(String sourceName, String destinationName, Integer time) {
        this.startTerminal = new Terminal(sourceName);
        this.endTerminal = new Terminal(destinationName);
        this.distance = time;
    }

    public int getDistance() {
        return distance;
    }

    public Terminal getStartTerminal() {
        return startTerminal;
    }

    public Terminal getEndTerminal() {
        return endTerminal;
    }
}
