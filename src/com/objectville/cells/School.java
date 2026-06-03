package com.objectville.cells;

import com.objectville.enums.ServiceType;

public class School extends ServiceProvider {

    public School(int x, int y) {
        super(x, y, 'S', 4, ServiceType.EDUCATION);
    }
}