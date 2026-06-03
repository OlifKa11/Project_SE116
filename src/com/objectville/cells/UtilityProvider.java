package com.objectville.cells;

import com.objectville.enums.UtilityType;

import com.objectville.engine.CityMap;
import com.objectville.interfaces.IPassable;

import java.util.ArrayList;

// Abstract classes for PowerPlant, WaterPumpingStation, InternetHub
public abstract class UtilityProvider extends Facility {
    protected int capacity;
    protected UtilityType utilityType;

    public UtilityProvider(int x, int y, char symbol, int capacity, UtilityType type) {
        super(x, y, symbol);
        this.capacity = capacity;
        this.utilityType = type;
    }

    public int getCapacity() { return capacity; }
    public UtilityType getUtilityType() { return utilityType; }

    //Distributes utility through connected cells using BFS
    public void distributeUtilityBFS(CityMap map) {
        ArrayList<Cell> queue = new ArrayList<>();
        ArrayList<Cell> visited = new ArrayList<>();
        queue.add(this);
        visited.add(this);

        int index = 0;
        int remaining = capacity;

        while (index < queue.size() && remaining > 0) {
            Cell current = queue.get(index);
            index++;

            for(Cell neighbor : map.getNeighbors(current)) {
                if(!visited.contains(neighbor)) {
                    visited.add(neighbor);

                    if(neighbor instanceof IPassable) {
                        IPassable passable = (IPassable) neighbor;

                        if(passable.canPassUtility()) {
                            queue.add(neighbor);

                            if(neighbor instanceof Zone) {
                                Zone zone = (Zone) neighbor;
                                int demand = zone.getUtilityDemand();

                                if(demand > remaining) {
                                    demand = remaining;
                                }

                                zone.receiveUtility(utilityType, demand);
                                remaining -= demand;
                                String zoneName = zone.getClass().getSimpleName();
                                if (zone instanceof Housing) {
                                    zoneName = "House";
                                }
                                System.out.println(zoneName + " at (" + zone.getY() + "," + zone.getX() + ") received " + demand + " " + utilityType.toString().toLowerCase());
                                remaining -= demand;
                            }
                        }
                    }
                }
            }
        }
    }
}
