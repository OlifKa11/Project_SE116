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
    public static void addGoods(int amount) {
        totalGoods += amount;
    }

    public static void addPopulation(int amount) {
        totalPopulation += amount;
    }

    public static void addLifestyle(int amount) {
        totalLifestyle += amount;
    }
}
