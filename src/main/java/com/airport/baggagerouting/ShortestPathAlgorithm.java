package com.airport.baggagerouting;

import com.airport.baggagerouting.model.TerminalConnection;

import java.util.List;

public interface ShortestPathAlgorithm {
    public String findShortestPath(String entry,String dest, List<TerminalConnection> graphEdges);
}
