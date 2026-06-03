package com.objectville.cells;

import com.objectville.enums.UtilityType;

public class PowerPlant extends UtilityProvider {

    public PowerPlant(int x, int y) {
        super(x, y, 'P', 100, UtilityType.ELECTRICITY);
    }
}