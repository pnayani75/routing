package com.airport.baggagerouting;

import com.airport.baggagerouting.model.Bag;
import com.airport.baggagerouting.model.TerminalConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan=  null;
        File baggageDataFile=new File("src/main/resources/InputTestData.txt");
        if(baggageDataFile.exists()){
            try {
                scan = new Scanner(baggageDataFile);
            }catch (FileNotFoundException ex){
                System.out.println("File is not available "+ ex.getMessage());
            }
        }else{
            System.out.println("File doesn't exists");
        }
        if(scan != null){
            List<TerminalConnection> edges= DataParser.parseInputGraph(scan);
            ShortestPathAlgorithm shortestPathAlgorithm = new ShortestPathAlgorithmImpl();
            Map<String,String> departuresMap=DataParser.getFlightDepartures(scan);
            List<Bag> bagList = DataParser.getBagInfo(scan);
            scan.close();
            for(Bag bag:bagList){
                String bagId=bag.getBagId();
                String entryGate=bag.getBagEntryGate();
                String flight = bag.getFlight();

                String destGate;
                if(flight.equals("ARRIVAL")){
                    destGate="BaggageClaim";
                }else{
                    destGate=departuresMap.get(flight);
                }
                String pathLine=shortestPathAlgorithm.findShortestPath(entryGate,destGate,edges);

                System.out.println(bagId+" "+pathLine);
            }
        }
    }


}
