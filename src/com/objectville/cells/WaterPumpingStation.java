package com.objectville.cells;

import com.objectville.enums.UtilityType;

public class WaterPumpingStation extends UtilityProvider {

    public WaterPumpingStation(int x, int y) {
        super(x, y, 'W', 100, UtilityType.WATER);
    }
}