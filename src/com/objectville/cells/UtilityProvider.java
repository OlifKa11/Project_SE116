package com.objectville.cells;

import com.objectville.enums.UtilityType;

import com.objectville.engine.CityMap;

// Abstract classes for PowerPlant, WaterStation, InternetHub
public abstract class UtilityProvider extends Facility {
    protected int capacitiy;
    protected UtilityType utilityType;

    public UtilityProvider(int x, int y, char symbol, int capacity, UtilityType type) {
        super(x, y, symbol);
        this.capacitiy = capacity;
        this.utilityType = type;
    }

    public int getCapacity() { return capacitiy; }
    public UtilityType getUtilityType() { return utilityType; }


    public void distributeUtilityBFS(CityMap map) {

    }
}