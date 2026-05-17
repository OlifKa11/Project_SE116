package com.objectville;
import com.objectville.cells.Cell;
public class Grid {
    private Cell [][] map;
    private int width;
    private int height;

    public Grid(int width, int height){
        this.width=width;
        this.height=height;
        this.map=new Cell[height][width];
    }
    public void setCell(int x, int y, Cell cell){
        if (isInside(x,y)){
            map[y][x] = cell;
        }
    }
    public Cell getCell(int x, int y){
        if (isInside(x,y)){
            return map[y][x];
        }
        return null;
    }
    public boolean isInside(int x, int y){
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
