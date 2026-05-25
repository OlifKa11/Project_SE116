package com.objectville;

import com.objectville.cells.Cell;
import com.objectville.engine.SimulationEngine;
import com.objectville.exceptions.SE116ConfigurationException;

public class ObjectVilleGame {
    private CityMap map;
    private int totalTicks;
    private ResourceManager manager;
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                throw new SE116ConfigurationException("Missing arguments! Usage: java -jar ObjectVilleGame.jar <map_file> <tick_count>");
            }
            ObjectVilleGame game = new ObjectVilleGame();
            game.map= new CityMap();
            game.map.loadMap(args[0]);
            game.manager = new ResourceManager();

            try {
                game.totalTicks = Integer.parseInt(args[1]);
                } catch (NumberFormatException e){
                throw new SE116ConfigurationException("Invalid tick count! It must be an integer. Provided: " + args[1]);
            }
            System.out.println("Map loaded successfully! Simulation is ready to run for " + game.totalTicks + " ticks.");
            game.runSimulation();
        } catch (SE116ConfigurationException e) {
            System.err.println("Configuration Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected system error occured: " + e.getMessage());
        }
    }

    public void runSimulation(){
        System.out.println("Simulation started...");
    }

}


