package com.objectville;
import com.objectville.cells.Zone;
import java.util.List;

public class ResourceManager {
    private int pooledPopulation;
    private int pooledGoods;
    private int pooledLifestyle;

    public ResourceManager(){
        this.pooledPopulation=0;
        this.pooledGoods=0;
        this.pooledLifestyle=0;
    }

    public void distributeResources(List<Zone> zones){
        System.out.println("Distributing resources from pools to zones...");
    }

    public void accumulateProduction(List<Zone> zones){
        System.out.println("Accumulating resources from zones back to pools...");
    }

    public int getPooledPopulation() {
        return pooledPopulation;
    }

    public int getPooledGoods() {
        return pooledGoods;
    }

    public int getPooledLifestyle() {
        return pooledLifestyle;
    }
}
