package com.airport.baggagerouting.model;

import java.util.*;

public class Terminal implements Comparable<Terminal> {

    private final String name;
    private int distance = Integer.MAX_VALUE;
    private Terminal predecessorTerminal = null;
    private Map<Terminal, Integer> adjacentConnections = new HashMap<>();

    public Terminal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Terminal getPredecessorTerminal() {
        return predecessorTerminal;
    }

    public void setPredecessorTerminal(Terminal predecessorTerminal) {
        this.predecessorTerminal = predecessorTerminal;
    }

    public Map<Terminal, Integer> getAdjacentConnections() {
        return adjacentConnections;
    }

    public void setAdjacentConnections(Map<Terminal, Integer> adjacentConnections) {
        this.adjacentConnections = adjacentConnections;
    }

    /**
     *
     * @param o is a terminal which is compared with this terminal
     * @return the int value after comparing the two terminals
     */
    @Override
    public int compareTo(Terminal o) {
        if(distance == o.distance)
            return name.compareTo(o.name);
        return Integer.compare(distance,o.distance);
    }

    /**
     * Determines shortest path from this terminal to all the connected ones
     * @return List<Terminal> that are connected an d their path respectively
     */
    public List<Terminal> getShortestPathTo()
    {
        List<Terminal> path = new ArrayList();
        path.add(this);
        Terminal vertex=this.getPredecessorTerminal();
        while (vertex!=null && !path.contains(vertex)) {
            path.add(vertex);
            vertex=vertex.getPredecessorTerminal();
        }

        Collections.reverse(path);
        return path;
    }

    @Override
    public String toString(){
        return this.name+":"+this.distance;
    }
}
