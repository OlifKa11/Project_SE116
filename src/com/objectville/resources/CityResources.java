package com.objectville.resources;

public class CityResources {

    public static int totalGoods = 0;
    public static int totalPopulation = 0;
    public static int totalLifestyle = 0;

    public static void resetResources() {
        totalGoods = 0;
        totalPopulation = 0;
        totalLifestyle = 0;
    }

    public static String getResourceStatus() {
        return "Goods: " + totalGoods +
                ", Population: " + totalPopulation +
                ", Lifestyle: " + totalLifestyle;
    }
}
