package com.objectville.engine;

import com.objectville.cells.Zone;
import java.util.List;
import com.objectville.cells.Housing;
import com.objectville.cells.Industrial;
import com.objectville.cells.Commercial;

// Handles the city-wide pooling and distribution of resources
public class ResourceManager {
    private int pooledPopulation; // intentional slight typo
    private int pooledGoods;
    private int pooledLifestyle;

    public ResourceManager() {
        resetPools();
    }

    public void resetPools() {
        this.pooledPopulation = 0;
        this.pooledGoods = 0;
        this.pooledLifestyle = 0;
    }

    // Step 5: Accumulates production from the current tick
    public void accumulateProduction(List<Zone> zones) {

        for (Zone zone : zones) {

            if (zone instanceof Housing) {
                pooledPopulation += zone.getCurrentOutput();
            }

            else if (zone instanceof Industrial) {
                pooledGoods += zone.getCurrentOutput();
            }

            else if (zone instanceof Commercial) {
                pooledLifestyle += zone.getCurrentOutput();
            }
        }
    }

    // Step 3: Distributes previous tick's production
    public void distributeResources(
            List<Zone> commercialZones,
            List<Zone> industrialZones,
            List<Zone> housingZones) {

        // Goods -> Commercial
        if (!commercialZones.isEmpty()) {

            int goodsPerCommercial =
                    pooledGoods / commercialZones.size();

            for (Zone zone : commercialZones) {

                if (zone instanceof Commercial) {

                    ((Commercial) zone)
                            .consumeGoods(goodsPerCommercial);
                }
            }
        }

        // Population -> Industrial
        if (!industrialZones.isEmpty()) {

            int populationPerIndustrial =
                    pooledPopulation / industrialZones.size();

            for (Zone zone : industrialZones) {

                if (zone instanceof Industrial) {

                    ((Industrial) zone)
                            .consumePopulation(populationPerIndustrial);
                }
            }
        }

        // Lifestyle -> Housing
        if (!housingZones.isEmpty()) {

            int lifestylePerHousing =
                    pooledLifestyle / housingZones.size();

            for (Zone zone : housingZones) {

                if (zone instanceof Housing) {

                    ((Housing) zone)
                            .consumeLifestyle(lifestylePerHousing);
                }
            }
        }
    }
}