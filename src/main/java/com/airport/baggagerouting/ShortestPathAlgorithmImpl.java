package com.airport.baggagerouting;

import com.airport.baggagerouting.model.RouteMap;
import com.airport.baggagerouting.model.Terminal;
import com.airport.baggagerouting.model.TerminalConnection;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShortestPathAlgorithmImpl implements ShortestPathAlgorithm {
    Map<String, RouteMap> visitedMap=new ConcurrentHashMap<>();

    /**
     * to find shortest path from source terminal to destination terminal
     * @param entry is source terminal
     * @param dest is destination terminal
     * @param graphEdges is the list of Terminal Connections in the airport
     * @return the string of path and time taken to get to the destination terminal
     */
    @Override
    public String findShortestPath(String entry, String dest, List<TerminalConnection> graphEdges) {
        RouteMap routeMap;
        if(visitedMap.containsKey(entry)){
            routeMap = visitedMap.get(entry);
        }else {
            routeMap = new RouteMap(graphEdges);
            routeMap.setGraphValues(entry);
            visitedMap.put(entry,routeMap);
        }

        List<Terminal> shortestPath= routeMap.getShortestPath(dest);
        return getTerminalPath(shortestPath);
    }

    /**
     *
     * @param path List<Terminal> that connects to the destination
     * @return string path from source and destination including distance
     */
    private String getTerminalPath(List<Terminal> path){
        StringBuffer line = new StringBuffer();

        for(Terminal terminal:path){
            line.append(terminal.getName()).append(" ");
        }
        line.append(": ").append(path.get(path.size()-1).getDistance());
        return line.toString();
    }
}
