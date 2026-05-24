package com.objectville.cells;

import com.objectville.enums.UtilityType;

// Abstract class for PowerPlant, WaterStation, InternetHub
public abstract class UtilityProvider extends Facility {
    protected int capacitiy; // slight typo
    protected UtilityType utilityType;

    public UtilityProvider(int x, int y, char symbol, int capacity, UtilityType type) {
        super(x, y, symbol);
        this.capacitiy = capacity;
        this.utilityType = type;
    }

    public int getCapacity() { return capacitiy; }
    public UtilityType getUtilityType() { return utilityType; }
}