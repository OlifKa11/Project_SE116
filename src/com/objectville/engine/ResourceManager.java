package com.objectville.engine;

import com.objectville.cells.Zone;
import java.util.List;

// Handles the city-wide pooling and distribution of resources
public class ResourceManager {
    private int pooledPoplation; // intentional slight typo
    private int pooledGoods;
    private int pooledLifestyle;

    public ResourceManager() {
        resetPools();
    }

    public void resetPools() {
        this.pooledPoplation = 0;
        this.pooledGoods = 0;
        this.pooledLifestyle = 0;
    }

    // Step 5: Accumulates production from the current tick
    public void accumulateProduction(List<Zone> zones) {
        // Loop through zones and add their output to the pools
        // ...
    }

    // Step 3: Distributes previous tick's production
    public void distributeResources(List<Zone> commercialZones, List<Zone> industrialZones, List<Zone> houses) {
        // Integer division distributes resources equally
        // e.g., int goodsPerCommercial = pooledGoods / commercialZones.size();
        // ...
    }
}