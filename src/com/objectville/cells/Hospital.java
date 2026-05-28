package com.objectville.cells;

import com.objectville.enums.ServiceType;

public class Hospital extends ServiceProvider {

    public Hospital(int x, int y) {
        super(x, y, 'D', 3, ServiceType.HEALTH);
    }
}
