package com.objectville.engine;

import com.objectville.cells.Cell;
import com.objectville.cells.Zone;
import com.objectville.interfaces.Updatable;
import com.objectville.interfaces.Distributable;
import com.objectville.exceptions.SE116ConfigurationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Core engine orchestrating the simulation cycles, entity updates, and resource distribution.
 * Implements a 5-step tick cycle to ensure consistent simulation state management.
 */
public class SimulationEngine {
    private Cell[][] map;
    private final List<Updatable> updatableEntities;
    private final List<Distributable> distributionSystems;
    private int tickCount;

    public SimulationEngine() {
        this.updatableEntities = new ArrayList<>();
        this.distributionSystems = new ArrayList<>();
        this.tickCount = 0;
    }

    /**
     * Prepares the engine with a loaded map and registers entities that require updates.
     * @param loadedMap The grid data provided by the map loader.
     * @throws SE116ConfigurationException if the map data is invalid.
     */
    public void setupSimulation(Cell[][] loadedMap) throws SE116ConfigurationException {
        if (loadedMap == null || loadedMap.length == 0) {
            throw new SE116ConfigurationException("Map grid cannot be null or empty.");
        }

        this.map = loadedMap;
        this.updatableEntities.clear();
        this.distributionSystems.clear();

        for (Cell[] row : map) {
            for (Cell cell : row) {
                if (cell instanceof Updatable) {
                    updatableEntities.add((Updatable) cell);
                }
            }
        }
    }

    /**
     * Adds a distribution system (e.g., UtilityDistributor) to the simulation.
     * @param system The system to be registered for periodic distribution.
     */
    public void addDistributionSystem(Distributable system) {
        this.distributionSystems.add(system);
    }

    /**
     * Runs the 5-step simulation cycle.
     */
    public void runTick() {
        // Step 1: Reset per-tick flags for all zones
        for (Cell[] row : map) {
            for (Cell cell : row) {
                if (cell instanceof Zone) {
                    ((Zone) cell).resetTickData();
                }
            }
        }

        // Step 2: Distribute resources (FIXED: now passing the map)
        for (Distributable system : distributionSystems) {
            system.distribute(map);
        }

        // Step 3: Handle entity-specific logic (e.g., leveling)
        for (Updatable entity : updatableEntities) {
            entity.update();
        }

        // Step 4: Execute economic production for zones
        for (Cell[] row : map) {
            for (Cell cell : row) {
                if (cell instanceof Zone) {
                    ((Zone) cell).produce();
                }
            }
        }

        // Step 5: Finalize tick and increment counter
        tickCount++;
    }

    public int getTickCount() { return tickCount; }
    public Cell[][] getMap() { return map; }
}