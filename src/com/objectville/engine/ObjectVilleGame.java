package com.objectville.engine;
import com.objectville.cells.*;

// The main simulation engine and entry point
public class ObjectVilleGame {
    private CityMap map;
    private ResourceManager resourceManager;
    private int totalTicks;

    public ObjectVilleGame(CityMap map, int ticks) {
        this.map = map;
        this.totalTicks = ticks;
        this.resourceManager = new ResourceManager();
    }

    // The core simulation loop
    public void runSimulation() {
        for (int currentTick = 1; currentTick <= totalTicks; currentTick++) {
            System.out.println("Tick " + currentTick);

            // Step 1: Services are provided (Radius based)
            step1_distributeServices();

            // Step 2: Utilities are distributed via BFS
            step2_distributeUtilities();

            // Step 3: Previous tick's production is distributed
            step3_distributeResources();

            // Step 4: Zones compute new level and output based on received utilities/resources
            step4_updateZones();

            // Step 5: New production is accumulated for the next tick
            step5_accumulateProduction();

            resetZones();
        }
    }

    //Finds ServiceProviders and applies services to zones in radius
    private void step1_distributeServices() {
        for (Cell cell : map.getAllCells()) {
            if (cell instanceof ServiceProvider) {
                ServiceProvider provider = (ServiceProvider) cell;
                provider.distributeService(map);
            }
        }
    }

    //Finds UtilityProviders and starts BFS
    private void step2_distributeUtilities() {
        for (Cell cell : map.getAllCells()) {
            if (cell instanceof UtilityProvider) {
                UtilityProvider provider = (UtilityProvider) cell;
                provider.distributeUtilityBFS(map);
            }
        }
    }

    private void step3_distributeResources() {
        resourceManager.distributeResources(map.getAllZones());
    }

    //Updates all zones and calculates output
    private void step4_updateZones() {
        for (Zone zone : map.getAllZones()) {
            zone.updateLevel();
            zone.calculateOutput();
        }
    }

    private void step5_accumulateProduction() {
        resourceManager.accumulateProduction(map.getAllZones());
        System.out.println("Tick production phase complete.");
    }

    private void resetZones() {
        for (Zone zone : map.getAllZones()) {
            zone.resetTickValues();
        }
    }

    // Entry point of the application
    public static void main(String[] args) {
        // Expected format: java -jar ObjectVilleGame.jar mymap.txt 5
        if (args.length < 2) {
            System.out.println("Usage: java -jar ObjectVilleGame.jar <mapfile> <ticks>");
            return;
        }

        String mapFile = args[0];
        int ticks = Integer.parseInt(args[1]);
        CityMap map = new CityMap(0,0);
        map.loadMap(mapFile);
        if(map.getAllCells().size()>0) {
            ObjectVilleGame game = new ObjectVilleGame(map, ticks);
            game.runSimulation();
        } else {
            System.out.println("Simulation aborted: Map grid is empty.");
        }
    }
}
