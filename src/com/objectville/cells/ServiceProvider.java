package com.objectville.cells;

import com.objectville.enums.ServiceType;

import com.objectville.engine.CityMap;

// Abstract class for Police, Hospital, School
public abstract class ServiceProvider extends Facility {
    protected int radius;
    protected ServiceType serviceType;

    public ServiceProvider(int x, int y, char symbol, int radius, ServiceType type) {
        super(x, y, symbol);
        this.radius = radius;
        this.serviceType = type;
    }

    public int getRadius() { return radius; }
    public ServiceType getServiceType() { return serviceType; }

    //Gives this service to all zones within radius
    public void distributeService(CityMap map) {
        for(Cell cell : map.getCellsWithinRadius(this, radius)) {
            if(cell instanceof Zone) {
                Zone zone = (Zone) cell;
                zone.receiveService(serviceType);
                System.out.println(zone.getClass().getSimpleName() + " at (" + zone.getY() + "," + zone.getX() + ") received " + serviceType.toString().toLowerCase() + " service");
            }
        }
    }
}