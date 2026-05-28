package com.objectville.engine;

import com.objectville.cells.Cell;
import java.util.ArrayList;
import java.util.List;

// Manages the grid and spatial queries for the simulation
public class CityMap {
    private Cell[][] grid;
    private int width;
    private int height;

    public CityMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];
    }

    // Returns a cell if within bounds
    public Cell getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[y][x];
        }
        return null;
    }

    // Used for Breadth-First Search (BFS) to find adjacent cells
    public List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();



        return neighbors;
    }

    // Used for Services (Police, Hospital, School)
    public List<Cell> getCellsWithinRadius(Cell center, int radius) {
        List<Cell> inRadius = new ArrayList<>();

        return inRadius;
    }
    public void printCity() {
        System.out.println("=== ObjectVille Current State ===");
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] != null) {
                    System.out.print("[" + grid[r][c].getSymbol() + "] ");
                } else {
                    System.out.print("[ ] ");
                }
            }
            System.out.println();
        }
        System.out.println("=================================");
    }
}