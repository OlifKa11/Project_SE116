package com.objectville.engine;

import com.objectville.cells.Cell;
import com.objectville.cells.Zone;
import com.objectville.interfaces.Updatable;
import com.objectville.interfaces.Distributable;
import com.objectville.exceptions.SE116ConfigurationException;
import java.util.ArrayList;
import java.util.List;

/**
 * The core engine responsible for managing the simulation grid and executing the
 * periodic simulation cycle. It orchestrates resource distribution, state updates,
 * and statistical reporting.
 */
public class SimulationEngine {
    private Cell[][] map;
    private final List<Updatable> updatableEntities;
    private final List<Distributable> distributionSystems;
    private int tickCount;

    /**
     * Initializes the simulation engine with empty entity lists and resets the tick counter.
     */
    public SimulationEngine() {
        this.updatableEntities = new ArrayList<>();
        this.distributionSystems = new ArrayList<>();
        this.tickCount = 0;
    }

    /**
     * Injects the pre-loaded map into the engine and scans for entities
     * that implement simulation interfaces.
     * @param loadedMap The 2D grid of Cells provided by the map loader.
     * @throws SE116ConfigurationException if the map is null or empty.
     */
    public void setupSimulation(Cell[][] loadedMap) throws SE116ConfigurationException {
        if (loadedMap == null || loadedMap.length == 0) {
            throw new SE116ConfigurationException("Provided map data is null or empty.");
        }

        this.map = loadedMap;
        this.updatableEntities.clear();
        this.distributionSystems.clear();

        // Scan the grid to identify updatable zones and other interactive components
        for (Cell[] row : map) {
            for (Cell cell : row) {
                if (cell instanceof Updatable) {
                    updatableEntities.add((Updatable) cell);
                }
            }
        }
    }

    /**
     * Registers a distribution system (e.g., UtilityDistributor) to the engine.
     * @param system The distribution logic to be executed during each tick.
     */
    public void addDistributionSystem(Distributable system) {
        this.distributionSystems.add(system);
    }

    /**
     * Executes the 5-step simulation cycle:
     * 1. Reset: Clears temporary tick data (e.g., utility connection status).
     * 2. Distribute: Runs resource distribution algorithms (Power, Water, Services).
     * 3. Update: Triggers growth and state logic for all updatable zones.
     * 4. Produce/Count: Finalizes economic production and gathers statistics.
     * 5. Report: Increments the simulation clock.
     */
    public void runTick() {
        // Step 1: Reset temporary state for all zones
        for (Cell[] row : map) {
            for (Cell cell : row) {
                if (cell instanceof Zone) {
                    ((Zone) cell).resetTickData();
                }
            }
        }

        // Step 2: Resource Distribution (Utilities and Services)
        for (Distributable system : distributionSystems) {
            system.distribute();
        }

        // Step 3: Individual Entity Updates (Leveling, etc.)
        for (Updatable entity : updatableEntities) {
            entity.update();
        }

        // Step 4: Production and Economic Simulation
        for (Cell[] row : map) {
            for (Cell cell : row) {
                if (cell instanceof Zone) {
                    ((Zone) cell).produce();
                }
            }
        }

        // Step 5: Clock Increment
        tickCount++;
    }

    // Getters for simulation state monitoring
    public int getTickCount() { return tickCount; }
    public Cell[][] getMap() { return map; }
}