package com.objectville.distribution;

import com.objectville.cells.Cell;
import com.objectville.cells.Zone;
import com.objectville.constants.UtilityTypes;
import com.objectville.interfaces.Distributable;

import java.util.LinkedList;
import java.util.Queue;

public class UtilityDistributor implements Distributable {
    private static final int PROVIDER_CAPACITY = 100;
    private final int[] dx = {-1, 1, 0, 0};
    private final int[] dy = {0, 0, -1, 1};

    //Distributes utilities from providers using breadth-first search
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

                String utilityType = getUtilityType(cell);

                if(utilityType != null) {
                    distributeFromProvider(map, cell, utilityType);
                }
            }
        }
    }

    private void distributeFromProvider(Cell[][] map, Cell provider, String utilityType) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<Cell> queue = new LinkedList<>();

        queue.add(provider);
        visited[provider.getY()][provider.getX()] = true;

        int remainingAmount = PROVIDER_CAPACITY;
        while(!queue.isEmpty() && remainingAmount > 0) {
            Cell current = queue.remove();

            if(current instanceof Zone) {
                Zone zone = (Zone) current;

                //Temporary version. Later this should use zone demand and subtract the absorbed amount
                zone.receiveUtility(utilityType);
                remainingAmount--;
            }

            for(int i = 0; i < dx.length; i++) {
                int nextX = current.getX() + dx[i];
                int nextY = current.getY() + dy[i];

                if(isInside(map, nextX, nextY) && !visited[nextY][nextX]) {
                    Cell nextCell = map[nextY][nextX];

                    if(isConnectable(nextCell)) {
                        queue.add(nextCell);
                        visited[nextY][nextX] = true;
                    }
                }
            }
        }
    }

    private boolean isInside(Cell[][] map, int x, int y) {
        return y >= 0 && y < map.length && x >= 0 && x < map[y].length;
    }

    private boolean isConnectable(Cell cell) {
        if(cell == null) {
            return false;
        }

        String className = cell.getClass().getSimpleName();

        if(cell instanceof Zone) { return true; }

        if(className.equals("Road")) { return true; }
        if(className.equals("PowerPlant")) { return true; }
        if(className.equals("WaterPumpingStation")) { return true; }
        if(className.equals("InternetHub")) { return true; }

        return false;
    }

    private String getUtilityType(Cell cell) {
        String className = cell.getClass().getSimpleName();

        if(className.equals("PowerPlant")) {
            return UtilityTypes.ELECTRICITY;
        }

        if(className.equals("WaterPumpingStation")) {
            return UtilityTypes.WATER;
        }

        if(className.equals("InternetHub")) {
            return UtilityTypes.INTERNET;
        }

        return null;
    }
}
