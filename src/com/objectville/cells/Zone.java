package com.objectville.cells;

import com.objectville.interfaces.Updatable;
import com.objectville.constants.UtilityTypes;

/**
 * Base class for all developable areas (Residential, Industrial, Commercial).
 * Handles utility status and basic growth requirements.
 */
public abstract class Zone extends Cell implements Updatable {
    protected int level;
    protected boolean isPowered;
    protected boolean isWatered;
    protected boolean isInternetConnected;
    protected int populationOrJobs;

    public Zone(int x, int y) {
        super(x, y);
        this.level = 1;
        this.isPowered = false;
        this.isWatered = false;
        this.isInternetConnected = false;
    }

    /**
     * Updates utility status based on the provided type. 
     * Prints a warning if an unrecognized type is passed to help with debugging.
     */
    public void receiveUtility(String type) {
        if (type.equals(UtilityTypes.ELECTRICITY)) {
            this.isPowered = true;
        } else if (type.equals(UtilityTypes.WATER)) {
            this.isWatered = true;
        } else if (type.equals(UtilityTypes.INTERNET)) {
            this.isInternetConnected = true;
        } else {
            // Elif'in önerisiyle eklenen güvenlik kontrolü
            System.out.println("Warning: Unknown utility type '" + type + "' received at Zone (" + x + "," + y + ")");
        }
    }

    /**
     * Checks if the zone has basic requirements to operate.
     */
    public boolean isOperational() {
        return isPowered && isWatered;
    }

    /**
     * Clears utility flags for the current tick. 
     * Called at the start of each cycle to re-verify resource access.
     */
    public void resetTickData() {
        this.isPowered = false;
        this.isWatered = false;
        this.isInternetConnected = false;
    }


    public int getLevel() {
        return level;
    }

    public boolean hasElectricity() {
        return isPowered;
    }

    public boolean hasWater() {
        return isWatered;
    }

    public boolean hasInternet() {
        return isInternetConnected;
    }

    public int getPopulationOrJobs() {
        return populationOrJobs;
    }

    public String getZoneStatus() {
        return "Zone Status -> Level: " + level +
                ", Electricity: " + isPowered +
                ", Water: " + isWatered +
                ", Internet: " + isInternetConnected +
                ", Population/Jobs: " + populationOrJobs;
    }
    public abstract void produce();
}
