package com.objectville.distribution;

import com.objectville.cells.Cell;
import com.objectville.cells.Zone;
import com.objectville.interfaces.Distributable;

//Distributes services from service buildings to nearby zones.
public class ServiceDistributor implements Distributable {

    //Provides services to zones within a radius
    @Override
    public void distribute(Cell[][] map) {
        if(map == null || map.length == 0) {
            return;
        }

        for(Cell[] row : map) {
            for(Cell cell : row) {
                if(cell == null) {
                    continue;
                }

                String serviceType = getServiceType(cell);
                int radius = getServiceRadius(cell);

                if(serviceType != null) {
                    distributeService(map, cell, serviceType, radius);
                }
            }
        }
    }

    private void distributeService(Cell[][] map, Cell serviceBuilding, String serviceType, int radius) {
        for(Cell[] row : map) {
            for(Cell cell : row) {
                if(cell instanceof Zone) {
                    int distance = getManhattanDistance(serviceBuilding, cell);

                    if(distance <= radius) {
                        //Temporary version. Later zone should have something like receiveService(serviceType)
                        System.out.println("Service " + serviceType + " reached zone at (" + cell.getX() + ", " + cell.getY() + ")");
                    }
                }
            }
        }
    }

    private int getManhattanDistance(Cell first, Cell second) {
        return Math.abs(first.getX() - second.getX()) + Math.abs(first.getY() - second.getY());
    }

    private String getServiceType(Cell cell) {
        String className = cell.getClass().getSimpleName();

        if(className.equals("PoliceStation")) {
            return "SECURITY";
        }

        if(className.equals("Hospital")) {
            return "HEALTH";
        }

        if(className.equals("School")) {
            return "EDUCATION";
        }

        return null;
    }

    private int getServiceRadius(Cell cell) {
        String className = cell.getClass().getSimpleName();

        if(className.equals("PoliceStation")) {
            return 5;
        }

        if(className.equals("Hospital")) {
            return 3;
        }

        if(className.equals("School")) {
            return 4;
        }

        return 0;
    }


}
