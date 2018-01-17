package com.airport.baggagerouting.model;

import java.util.*;

public class RouteMap {

    private final Map<String, Terminal> graphMap;
    public RouteMap(List<TerminalConnection> connectionList){
        graphMap = new HashMap<>(connectionList.size());

        for (TerminalConnection connection:connectionList) {
            if(!graphMap.containsKey(connection.getStartTerminal().getName())) graphMap.put(connection.getStartTerminal().getName(),new Terminal(connection.getStartTerminal().getName()));
            if(!graphMap.containsKey(connection.getEndTerminal().getName())) graphMap.put(connection.getEndTerminal().getName(),new Terminal(connection.getEndTerminal().getName()));
        }
        for (TerminalConnection connection: connectionList) {
            graphMap.get(connection.getStartTerminal().getName()).getAdjacentConnections().put(graphMap.get(connection.getEndTerminal().getName()),connection.getDistance());
        }
        
    }

    /**
     * This method will set the source Terminal distance and predecessor terminal
     * @param sourceTerminal
     */
    public void setGraphValues(String sourceTerminal) {
        if (!graphMap.containsKey(sourceTerminal)) {
            System.err.println("Map doesn't have a source terminal named "+sourceTerminal);
        }
        final Terminal source = graphMap.get(sourceTerminal);
        PriorityQueue<Terminal> queue = new PriorityQueue<>();


        for (Terminal t : graphMap.values()) {
            t.setPredecessorTerminal(t == source ? source : null);
            t.setDistance(t== source? 0 : Integer.MAX_VALUE);
            queue.add(t);
        }

        computeShortestPath(queue);
    }

    /**
     * This method will compute all the connections of source terminal and their distances
     * @param que is the priority queue all the Terminals present in the graph
     */
    private void computeShortestPath(final PriorityQueue<Terminal> que) {
        Terminal source, neighbour;
        while (!que.isEmpty()) {

            source = que.poll();
            if (source.getDistance() == Integer.MAX_VALUE) break;

            //look at distances to each neighbour
            for (Map.Entry<Terminal,Integer> a : source.getAdjacentConnections().entrySet()) {
                neighbour = a.getKey();
                int newDistance = source.getDistance() + a.getValue();
                if (newDistance < neighbour.getDistance()) {
                     que.remove(neighbour);
                     neighbour.setDistance(newDistance);
                     neighbour.setPredecessorTerminal(source);
                     que.add(neighbour);
                }


            }
        }
    }
    public List<Terminal> getShortestPath(String endTerminal){
        if (!graphMap.containsKey(endTerminal)) {
            System.err.println("Map doesn't have a end terminal "+ endTerminal);

        }

        return graphMap.get(endTerminal).getShortestPathTo();
    }
}
