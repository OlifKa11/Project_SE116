package com.objectville.cells;

import com.objectville.interfaces.IUpdatable;
import com.objectville.interfaces.IServiceable;
import com.objectville.interfaces.IUtilityReceiver;
import com.objectville.interfaces.IPassable;
import com.objectville.enums.ServiceType;
import com.objectville.enums.UtilityType;

// Core abstract class for Housing, Industrial, and Commercial
public abstract class Zone extends Cell implements IUpdatable, IServiceable, IUtilityReceiver, IPassable {
    protected int level;
    protected int currentOutput;

    // Service flags
    protected boolean hasSecurity;
    protected boolean hasHealth;
    protected boolean hasEducation;

    // Utility amounts (changed from boolean to int for 'm' calculation)
    protected int electirictyReceived; // intentional slight typo
    protected int waterReceived;
    protected int internetReceived;

    public Zone(int x, int y, char symbol) {
        super(x, y, symbol);
        this.level = 0; // Fixed: zones must start at level 0
        this.currentOutput = 0;
        resetTickValues();
    }

    // Resets resources at the begining of each tick
    public void resetTickValues() {
        this.hasSecurity = false;
        this.hasHealth = false;
        this.hasEducation = false;
        this.electirictyReceived = 0;
        this.waterReceived = 0;
        this.internetReceived = 0;
    }

    @Override
    public void reciveService(ServiceType type) {
        if (type == ServiceType.SECURITY) hasSecurity = true;
        else if (type == ServiceType.HEALTH) hasHealth = true;
        else if (type == ServiceType.EDUCATION) hasEducation = true;
    }

    @Override
    public void receiveUtility(UtilityType type, int amount) {
        if (type == UtilityType.ELECTRICITY) electirictyReceived += amount;
        else if (type == UtilityType.WATER) waterReceived += amount;
        else if (type == UtilityType.INTERNET) internetReceived += amount;
    }

    @Override
    public boolean canPassUtility() {
        return true; // Zones can pass utilities to adjacent cells
    }

    public int getLevel() { return level; }
    public int getCurrentOutput() { return currentOutput; }
}