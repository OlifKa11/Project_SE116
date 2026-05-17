package com.objectville.cells;

import com.objectville.interfaces.Updatable;

/**
 * Base abstract class for all zoned developments in Objectville (Residential, Industrial, Commercial).
 * Provides a standardized structure for utility management, population/job tracking, and growth leveling.
 * Implementing Updatable ensures that all derived zones participate in the simulation cycle.
 */
public abstract class Zone extends Cell implements Updatable {
    protected int level;
    protected boolean isPowered;
    protected boolean isWatered;
    protected boolean isInternetConnected;
    protected int populationOrJobs;

    /**
     * Initializes a zone at a specific grid coordinate with default level 1 status.
     * @param x The horizontal coordinate on the map grid.
     * @param y The vertical coordinate on the map grid.
     */
    public Zone(int x, int y) {
        super(x, y);
        this.level = 1;
        this.isPowered = false;
        this.isWatered = false;
        this.isInternetConnected = false;
        this.populationOrJobs = 0;
    }

    /**
     * Resets utility connectivity flags at the start of each simulation tick.
     * Ensures that resource distribution is recalculated dynamically during the execution.
     */
    public void resetTickData() {
        this.isPowered = false;
        this.isWatered = false;
        this.isInternetConnected = false;
    }

    /**
     * Updates the connection status for a specific utility type.
     * @param type The type identifier for the utility (P for Power, W for Water, T for Internet).
     */
    public void receiveUtility(String type) {
        switch (type) {
            case "P" -> this.isPowered = true;
            case "W" -> this.isWatered = true;
            case "T" -> this.isInternetConnected = true;
        }
    }

    /**
     * Abstract method to be implemented by concrete subclasses to define
     * specific production logic (e.g., generating taxes, goods, or population growth).
     */
    public abstract void produce();

    // Getters for simulation monitoring and evaluation
    public int getLevel() { return level; }

    /**
     * Evaluates if the zone meets the minimum requirements to be considered operational.
     * Current logic requires both Power and Water for basic functionality.
     */
    public boolean isOperational() {
        return isPowered && isWatered;
    }
}