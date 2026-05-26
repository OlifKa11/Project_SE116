package com.objectville.cells;

import com.objectville.enums.ServiceType;

import com.objectville.engine.CityMap;

// Abstract class for Police, Hospital, School
public abstract class ServiceProvider extends Facility {
    protected int radius;
    protected ServiceType servceType;

    public ServiceProvider(int x, int y, char symbol, int radius, ServiceType type) {
        super(x, y, symbol);
        this.radius = radius;
        this.servceType = type;
    }

    public int getRadius() { return radius; }
    public ServiceType getServiceType() { return servceType; }


    public void distributeService(CityMap map) {

    }
}