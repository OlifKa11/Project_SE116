package com.objectville.engine;

import com.objectville.cells.*;
import java.util.List;

public class ResourceManager {
    private int pooledPopulation;
    private int pooledGoods;
    private int pooledLifestyle;

    public ResourceManager() {
        this.pooledPopulation = 0;
        this.pooledGoods = 0;
        this.pooledLifestyle = 0;
    }

    // Step 5: Accumulates production from the current tick
    public void accumulateProduction(List<Zone> zones) {

        this.pooledPopulation = 0;
        this.pooledGoods = 0;
        this.pooledLifestyle = 0;

        // Loop through zones and add their output to the pools
        for (Zone zone : zones) {
            if (zone instanceof Housing) {
                this.pooledPopulation += zone.getCurrentOutput();
            } else if (zone instanceof Industrial) {
                this.pooledGoods += zone.getCurrentOutput();
            } else if (zone instanceof Commercial) {
                this.pooledLifestyle += zone.getCurrentOutput();
            }
        }
    }

    // Step 3: Distributes previous tick's production
    public void distributeResources(List<Zone> zones) {
        // Count zone types for equal distribution
        int commCount = 0, indCount = 0, houseCount = 0;
        for (Zone z : zones) {
            if (z instanceof Commercial) commCount++;
            else if (z instanceof Industrial) indCount++;
            else if (z instanceof Housing) houseCount++;
        }

        // Distribute mathematically avoiding divide-by-zero
        for (Zone zone : zones) {
            if (zone instanceof Commercial && commCount > 0) {
                ((Commercial) zone).receivePopulation(this.pooledPopulation / commCount);
                ((Commercial) zone).receiveGoods(this.pooledGoods / commCount);
            } else if (zone instanceof Industrial && indCount > 0) {
                ((Industrial) zone).receivePopulation(this.pooledPopulation / indCount);
            } else if (zone instanceof Housing && houseCount > 0) {
                ((Housing) zone).receiveLifestyle(this.pooledLifestyle / houseCount);
            }
        }
    }
}