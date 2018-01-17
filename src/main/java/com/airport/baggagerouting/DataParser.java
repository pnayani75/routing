package com.airport.baggagerouting;

import com.airport.baggagerouting.model.Bag;
import com.airport.baggagerouting.model.TerminalConnection;

import java.util.*;

public class DataParser {
    /**
     *
     * @param scanner as a input for scanning the text from the input file
     * @return the List<TerminalConnection> from the input file
     */
    public static List<TerminalConnection> parseInputGraph(Scanner scanner){
        String graphSection=scanner.nextLine();
        if(!graphSection.startsWith("# Section:")){
            throw new IllegalArgumentException("Input file doesn't have a section header");
        }
        String next=scanner.nextLine();
        List<TerminalConnection> edges=new ArrayList<>();
        while (!next.startsWith("# Section:")){
            String[] parts=next.trim().split("\\s+");
            if(parts.length>=3) {
                TerminalConnection terminalConnection = new TerminalConnection(parts[0],parts[1],Integer.valueOf(parts[2]));
                edges.add(terminalConnection);

                TerminalConnection reverseTerminalConnection = new TerminalConnection(parts[1],parts[0],Integer.valueOf(parts[2]));
                edges.add(reverseTerminalConnection);
            }else{
                throw new IllegalArgumentException("Format of the terminal connection is wrong");
            }
            next=scanner.nextLine();
        }
        return edges;
    }

    /**
     *
     * @param scanner as a input for scanning the text from the input file
     * @return the Map<> where is Flight number and Value is Destination     */
    public static Map<String,String> getFlightDepartures(Scanner scanner){
        String next=scanner.nextLine();
        Map<String,String> departuresMap=new HashMap<>();
        while(!next.startsWith("# Section:")){
            String [] parts=next.trim().split("\\s+");
            if(parts.length >= 2){
                departuresMap.put(parts[0],parts[1]);
            }else{
                throw new IllegalArgumentException("There is no enough amount of data to parse flight departure info");
            }
            next=scanner.nextLine();
        }
        return departuresMap;
    }

    /**
     *
     * @param scanner as a input for scanning the text from the input file
     * @return the List<Bag>
     */
    public static List<Bag> getBagInfo(Scanner scanner){
        String next ;
        List<Bag> bagList= new ArrayList<>();
        do{
            next = scanner.nextLine();
            String [] parts = next.trim().split("\\s+");
            if(parts.length >=3){
                Bag bag= new Bag(parts[0],parts[1],parts[2]);
                bagList.add(bag);
            }else{
                scanner.close();
                break;
            }
        }while(scanner.hasNextLine());
        return bagList;
    }
}
