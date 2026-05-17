package com.objectville;

import com.objectville.cells.Cell;
import com.objectville.interfaces.Updatable;
import com.objectville.interfaces.Distributable;
import java.util.List;
import java.util.ArrayList;

public class SimulationEngine {
    private Cell[][] map;
    private int tickCount;
    private List<Updatable> updatableEntities;
    private List<Distributable> distributionSystems;

    public SimulationEngine() {
        this.tickCount = 0;
        this.updatableEntities = new ArrayList<>();
        this.distributionSystems = new ArrayList<>();
        this.map = null;
    }

    public void setupSimulation(Cell[][] loadedMap) {
        this.map = loadedMap;
    }

    public void runTick() {
        this.tickCount++;
    }

    public void setCell(int x, int y, Cell cell) {
        if (map != null && isInside(x, y)) {
            map[y][x] = cell;
        }
    }

    public Cell getCell(int x, int y) {
        if (map != null && isInside(x, y)) {
            return map[y][x];
        }
        return null;
    }

    public boolean isInside(int x, int y) {
        if (map == null) return false;
        int height = map.length;
        int width = map[0].length;
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}