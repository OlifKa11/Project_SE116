package com.objectville.engine;

import com.objectville.cells.Cell;
import com.objectville.cells.Zone;

import java.time.ZoneId;
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
        if (cell == null) {
            return neighbors;
        }
        int x = cell.getX();
        int y = cell.getY();
        int[] dy={-1,1,0,0};
        int[] dx={0,0,-1,1};
        for (int i=0; i<4; i++){
            int nextY = y + dy[i];
            int nextX = x + dx[i];
            if (nextX>=0 && nextX<width && nextY>=0 && nextY<height){
                Cell neighbor = grid[nextX][nextY];
                if (neighbor != null){
                    neighbors.add(neighbor);
                }
            }
        }
        return neighbors;
    }

    // Used for Services (Police, Hospital, School)
    public List<Cell> getCellsWithinRadius(Cell center, int radius) {
        List<Cell> inRadius = new ArrayList<>();
        if (center == null || radius < 0) {
            return inRadius;
        }
        int centerX = center.getX();
        int centerY = center.getY();
        int startY = Math.max(0, centerY - radius);
        int endY = Math.min(height - 1, centerY + radius);
        int startX = Math.max(0, centerX - radius);
        int endX = Math.min(width - 1, centerX + radius);
        for (int r = startY; r <= endY; r++) {
            for (int c = startX; c <= endX; c++) {
                int distance = Math.abs(centerX - c) + Math.abs(centerY - r);
                if (distance <= radius) {
                    Cell cell = grid[r][c];
                    if (cell != null) {
                        inRadius.add(cell);
                    }
                }
            }
        }
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

    public List<Cell> getAllCells(){
        List<Cell> allCells = new ArrayList<>();
        for (int r=0; r<height; r++){
            for (int c=0; c<width; c++){
                if (grid[r][c]!=null){
                    allCells.add(grid[r][c]);
                }
            }
        }
        return allCells;
    }

    public List<Zone> getAllZones(){
        List<Zone> allZones = new ArrayList<>();
        for (int r=0; r<height; r++){
            for (int c=0; c<width; c++){
                if (grid[r][c] instanceof Zone){
                    allZones.add((Zone) grid [r][c]);
                }
            }
        }
        return allZones;

    }


}