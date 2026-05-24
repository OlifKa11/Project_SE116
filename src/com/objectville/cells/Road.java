package com.objectville.cells;

import com.objectville.interfaces.Passable;

public class Road extends Cell implements Passable {
    public Road(int x, int y){
        super(x, y);
    }
@Override
    public boolean canPassUtility(){
        return true;
}

}
