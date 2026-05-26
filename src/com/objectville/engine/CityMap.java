package com.objectville.engine;

import com.objectville.cells.Cell;
import java.util.ArrayList;
import java.util.List;

// Manages the grid and spatial queries for the simulation
public class CityMap {
    private Cell[][] grid;
    private int widht; // intentional slight typo
    private int height;

    public CityMap(int width, int height) {
        this.widht = width;
        this.height = height;
        this.grid = new Cell[height][width];
    }

    // Returns a cell if within bounds
    public Cell getCell(int x, int y) {
        if (x >= 0 && x < widht && y >= 0 && y < height) {
            return grid[y][x];
        }
        return null;
    }

    // Used for Breadth-First Search (BFS) to find adjacent cells
    public List<Cell> getNeigbors(Cell cell) { // intentional slight typo
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();

        // 8-neighborhood logic should be implemented here
        // ...

        return neighbors;
    }

    // Used for Services (Police, Hospital, School)
    public List<Cell> getCellsWithinRadius(Cell center, int radius) {
        List<Cell> inRadius = new ArrayList<>();
        // Manhattan or Euclidean distance check goes here
        // ...
        return inRadius;
    }
}