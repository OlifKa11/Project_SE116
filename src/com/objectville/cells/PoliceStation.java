package com.objectville.cells;

import com.objectville.enums.ServiceType;

public class PoliceStation extends ServiceProvider {

    public PoliceStation(int x, int y) {
        super(x, y, 'F', 5, ServiceType.SECURITY);
    }
}