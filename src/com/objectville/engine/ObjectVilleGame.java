package com.objectville.engine;
import com.objectville.cells.*;

// The main simulation engine and entry point
public class ObjectVilleGame {
    private CityMap map;
    private ResourceManager resorceManager;
    private int totalTicks;

    public ObjectVilleGame(CityMap map, int ticks) {
        this.map = map;
        this.totalTicks = ticks;
        this.resorceManager = new ResourceManager();
    }

    // The core simulation loop
    public void runSimulation() {
        for (int currentTick = 1; currentTick <= totalTicks; currentTick++) {

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
        }
    }

    private void step1_distributeServices() {
        // Logic to find ServiceProviders and apply to zones in radius
    }

    private void step2_distributeUtilities() {
        // Logic to find UtilityProviders and start BFS
    }

    private void step3_poolAndDistributeResources(CityMap cityMap) {
        int totalPopulation = 0;
        int totalGoods = 0;
        int totalLifestyle = 0;
        Cell[][] grid = cityMap.getGrid();


        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                Cell cell = grid[r][c];
                if (cell instanceof Housing) {
                    totalPopulation += ((Housing) cell).getCurrentOutput();
                } else if (cell instanceof Industrial) {
                    totalGoods += ((Industrial) cell).getCurrentOutput();
                } else if (cell instanceof Commercial) {
                    totalLifestyle += ((Commercial) cell).getCurrentOutput();
                }
            }
        }


        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                Cell cell = grid[r][c];
                if (cell instanceof Industrial) {
                    ((Industrial) cell).receivePopulation(totalPopulation / 10);
                } else if (cell instanceof Commercial) {
                    ((Commercial) cell).receivePopulation(totalPopulation / 5);
                    ((Commercial) cell).receiveGoods(totalGoods / 5);
                } else if (cell instanceof Housing) {
                    ((Housing) cell).receiveLifestyle(totalLifestyle / 10);
                }
            }
        }
    }

    private void step4_updateZones() {
        // Loops through all zones and calls updateLevel() and calculateOutput()
    }

    private void step5_accumulateProduction(Cell[][] grid) {
        int currentTickTotalOutput = 0;


        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] instanceof Zone) {
                    Zone zone = (Zone) grid[r][c];
                    currentTickTotalOutput += zone.getCurrentOutput();
                }
            }
        }


        System.out.println("Tick production phase complete. Total output generated: " + currentTickTotalOutput);
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

        // CityMap map = loadMapFromFile(mapFile); // Java IO implementation needed here
        // ObjectVilleGame game = new ObjectVilleGame(map, ticks);
        // game.runSimulation();
    }
}
