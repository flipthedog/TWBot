package sample;

import org.openqa.selenium.By;

public class Building implements Data{

    public Building(){

    }

    /*
    0 - Overview
    1 - Headquarters
    2 - Rallypoint
    3 - Statue
    4 - Barracks
    5 - Stable
    6 - Workshop
    7 - Watchtower
    8 - Academy
    9 - Smithy
    10 - Market
    11 - Wall
    12 - Timber Camp
    13 - Clay Camp
    14 - Iron Camp
    15 - Farm
    16 - Warehouse
    17 - Hiding Place
     */
    public static void goToScreen(int building) {
        switch (building) {
            case 0:
                // Overview
                data.driver.get(data.homeURL + "overview");
                break;
            case 1:
                // Headquarters
                data.driver.get(data.homeURL + "main");
                break;
            case 2:
                // Rallypoint
                data.driver.get(data.homeURL + "place");
                break;
            case 3:
                // Statue
                data.driver.get(data.homeURL + "statue");
                break;
            case 4:
                // Barracks
                data.driver.get(data.homeURL + "barracks");
                break;
            case 5:
                // Stable
                data.driver.get(data.homeURL + "stable");
                break;
            case 6:
                // Workshop
                data.driver.get(data.homeURL + "garage");
                break;
            case 7:
                // Watchtower
                data.driver.get(data.homeURL + "watchtower");
                break;
            case 8:
                // Academy
                data.driver.get(data.homeURL + "snob");
                break;
            case 9:
                // Smithy
                data.driver.get(data.homeURL + "smith");
                break;
            case 10:
                // Market
                data.driver.get(data.homeURL + "market");
                break;
            case 11:
                // Wall
                data.driver.get(data.homeURL + "wall");
                break;
            case 12:
                // Timber Camp
                data.driver.get(data.homeURL + "wood");
                break;
            case 13:
                // Clay camp
                data.driver.get(data.homeURL + "stone");
                break;
            case 14:
                // Iron camp
                data.driver.get(data.homeURL + "iron");
                break;
            case 15:
                // Farm
                data.driver.get(data.homeURL + "farm");
                break;
            case 16:
                // Warehouse
                data.driver.get(data.homeURL + "storage");
                break;
            case 17:
                // Hiding Place
                data.driver.get(data.homeURL + "hide");
                break;
        }
    }

    public static void goToScreen(String building) {
        if(building.equals("overview")) {
            data.driver.get(data.homeURL + "overview");
        } else if (building.equals("main")) {
            data.driver.get(data.homeURL + "main");
        } else if (building.equals("place")) {
            data.driver.get(data.homeURL + "place");
        } else if (building.equals("statue")) {
            data.driver.get(data.homeURL + "statue");
        } else if (building.equals("barracks")) {
            data.driver.get(data.homeURL + "barracks");
        } else if (building.equals("stable")) {
            data.driver.get(data.homeURL + "stable");
        } else if (building.equals("garage")) {
            data.driver.get(data.homeURL + "garage");
        } else if (building.equals("watchtower")) {
            data.driver.get(data.homeURL + "watchtower");
        } else if (building.equals("snob")) {
            data.driver.get(data.homeURL + "snob");
        } else if (building.equals("smith")) {
            data.driver.get(data.homeURL + "smith");
        } else if (building.equals("market")) {
            data.driver.get(data.homeURL + "market");
        } else if (building.equals("wall")) {
            data.driver.get(data.homeURL + "wall");
        } else if (building.equals("wood")) {
            data.driver.get(data.homeURL + "wood");
        } else if (building.equals("stone")) {
            data.driver.get(data.homeURL + "stone");
        } else if (building.equals("iron")) {
            data.driver.get(data.homeURL + "iron");
        } else if (building.equals("farm")) {
            data.driver.get(data.homeURL + "farm");
        } else if (building.equals("storage")) {
            data.driver.get(data.homeURL + "storage");
        } else if (building.equals("hide")){
            data.driver.get(data.homeURL + "hide");
        }
    }

    public static void build(String building) {
        goToScreen(1);

        if(building.equals("overview")) {
            System.out.println("Can't build overview");
        } else if (building.equals("main")) {
            data.driver.findElement(By.id("main_buildrow_main")).findElement(By.className("btn-build")).click();
        } else if (building.equals("place")) {
            data.driver.findElement(By.id("main_buildrow_place")).findElement(By.className("btn-build")).click();
        } else if (building.equals("statue")) {
            data.driver.findElement(By.id("main_buildrow_statue")).findElement(By.className("btn-build")).click();
        } else if (building.equals("barracks")) {
            data.driver.findElement(By.id("main_buildrow_barracks")).findElement(By.className("btn-build")).click();
        } else if (building.equals("stable")) {
            data.driver.findElement(By.id("main_buildrow_stable")).findElement(By.className("btn-build")).click();
        } else if (building.equals("garage")) {
            data.driver.findElement(By.id("main_buildrow_garage")).findElement(By.className("btn-build")).click();
        } else if (building.equals("watchtower")) {
            data.driver.findElement(By.id("main_buildrow_watchtower")).findElement(By.className("btn-build")).click();
        } else if (building.equals("snob")) {
            data.driver.findElement(By.id("main_buildrow_snob")).findElement(By.className("btn-build")).click();
        } else if (building.equals("smith")) {
            data.driver.findElement(By.id("main_buildrow_smith")).findElement(By.className("btn-build")).click();
        } else if (building.equals("market")) {
            data.driver.findElement(By.id("main_buildrow_market")).findElement(By.className("btn-build")).click();
        } else if (building.equals("wall")) {
            data.driver.findElement(By.id("main_buildrow_wall")).findElement(By.className("btn-build")).click();
        } else if (building.equals("wood")) {
            data.driver.findElement(By.id("main_buildrow_wood")).findElement(By.className("btn-build")).click();
        } else if (building.equals("stone")) {
            data.driver.findElement(By.id("main_buildrow_stone")).findElement(By.className("btn-build")).click();
        } else if (building.equals("iron")) {
            data.driver.findElement(By.id("main_buildrow_iron")).findElement(By.className("btn-build")).click();
        } else if (building.equals("farm")) {
            data.driver.findElement(By.id("main_buildrow_farm")).findElement(By.className("btn-build")).click();
        } else if (building.equals("storage")) {
            data.driver.findElement(By.id("main_buildrow_storage")).findElement(By.className("btn-build")).click();
        } else if (building.equals("hide")){
            data.driver.findElement(By.id("main_buildrow_hide")).findElement(By.className("btn-build")).click();
        }
        goToScreen(0);
    }
}
