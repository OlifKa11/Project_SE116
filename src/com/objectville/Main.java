package com.objectville;

import com.objectville.cells.Cell;
import com.objectville.cells.CommercialZone;
import com.objectville.cells.IndustrialZone;
import com.objectville.cells.ResidentialZone;
import com.objectville.engine.SimulationEngine;
import com.objectville.exceptions.SE116ConfigurationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                throw new SE116ConfigurationException("Error: Missing argument!");
            }
            String fileName = args[0];
            int totalTicks;

            try {
                totalTicks = Integer.parseInt(args[1]);
                } catch (NumberFormatException e){
                throw new SE116ConfigurationException("Invalid tick count! It must be an integer. Provided: " + args[1]);
            }
            Cell[][] loadedMap = readAndParseMap(fileName);
            SimulationEngine engine = new SimulationEngine();
            engine.setupSimulation(loadedMap);
            System.out.println("Map loaded successfully! Simulation is ready to run for " + totalTicks + " ticks.");
        } catch (SE116ConfigurationException e) {
            System.err.println("Configuration Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected system error occured: " + e.getMessage());
        }
    }
    private static Cell[][] readAndParseMap(String fileName) throws SE116ConfigurationException, IOException{
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
        int width = lines.get(0).length();
        int height = lines.size();

        for (int i=1; i< lines.size(); i++){
            if (lines.get(i).length() != width){
                throw new SE116ConfigurationException("Invalid map format. Line " + (i+1) + " lenght does not match the other lines");
            }
        }
        Cell[][] map = new Cell[height][width];

        for (int y=0; y<height; y++){
            String currentLine = lines.get(y);
            for (int x=0; x<width; x++){
                char symbol = currentLine.charAt(x);

                switch (symbol){
                    case 'H':
                        map[y][x] = new ResidentialZone(x,y);
                    case 'C':
                        map[y][x] = new CommercialZone(x,y);
                    case 'I':
                        map[y][x] = new IndustrialZone(x,y);
                    case 'E':
                        map[y][x] = null;
                    case 'R':
                        map[y][x] = null;
                    case 'S':
                        map[y][x] = null;
                    case 'D':
                        map[y][x] = null;
                    case 'T':
                        map[y][x] = null;
                    case 'W':
                        map[y][x] = null;
                    case 'P':
                        map[y][x] = null;
                    case 'F':
                        map[y][x] = null;
                        break;
                    default:
                        throw new SE116ConfigurationException("Invalid symbol found in map! Row: " + (y + 1) + ", Column: " + (x + 1) + " -> '" + symbol + "'");
                }
            }
        }
        return map;
    }
}


