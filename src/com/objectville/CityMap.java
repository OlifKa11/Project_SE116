package com.objectville;

import com.objectville.cells.*;
import com.objectville.exceptions.SE116ConfigurationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CityMap {
    private Cell[][] grid;
    private int width;
    private int height;
    public void loadMap(String fileName) throws SE116ConfigurationException, IOException{
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line=reader.readLine())!=null){
                lines.add(line.trim());
            }
        } catch (IOException e) {
            throw new SE116ConfigurationException("Map file could not be found or read -> " + fileName);
        }
        if (lines.isEmpty()){
            throw new SE116ConfigurationException("The map file is empty.");
        }
        this.width = lines.get(0).length();
        this.height = lines.size();

        for (int i=1; i< lines.size(); i++){
            if (lines.get(i).length() != width){
                throw new SE116ConfigurationException("Invalid map format. Line " + (i+1) + " lenght does not match the other lines");
            }
        }
        this.grid = new Cell[height][width];

        for (int y=0; y<height; y++){
            String currentLine = lines.get(y);
            for (int x=0; x<width; x++){
                char symbol = currentLine.charAt(x);

                switch (symbol){
                    case 'H':
                        this.grid[y][x] = new ResidentialZone(x,y);
                    case 'C':
                        this.grid[y][x] = new CommercialZone(x,y);
                    case 'I':
                        this.grid[y][x] = new IndustrialZone(x,y);
                    case 'E':
                        this.grid[y][x] = null;
                    case 'R':
                        this.grid[y][x] = null;
                    case 'S':
                        this.grid[y][x] = null;
                    case 'D':
                        this.grid[y][x] = null;
                    case 'T':
                        this.grid[y][x] = null;
                    case 'W':
                        this.grid[y][x] = null;
                    case 'P':
                        this.grid[y][x] = null;
                    case 'F':
                        this.grid[y][x] = null;
                        break;
                    default:
                        throw new SE116ConfigurationException("Invalid symbol found in map! Row: " + (y + 1) + ", Column: " + (x + 1) + " -> '" + symbol + "'");
                }
            }
        }
    }
    public Cell getCell(int x, int y){
        return grid[y][x];
    }

}
