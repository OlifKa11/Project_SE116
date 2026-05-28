package com.objectville.cells;

import com.objectville.enums.UtilityType;

public class InternetHub extends UtilityProvider {

    public InternetHub(int x, int y) {
        super(x, y, 'T', 100, UtilityType.INTERNET);
    }
}