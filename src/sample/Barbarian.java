package sample;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Barbarian {
    private LinkedList<Point2D> barbVillages;
    private int currentVillage;

    public Barbarian() {
        System.out.println("Initialized barbarian");
        barbVillages = new LinkedList<>();
        currentVillage = 0;
    }

    /**
     * Add a village to regularly farm
     * @param xCoord x coordinate of village
     * @param yCoord y coordinate of village
     */
    public void addVillage(int xCoord, int yCoord) {
        barbVillages.add(new Point(xCoord,yCoord));
    }

    /**
     * Get the next farm village
     * @return point of the farm village
     */
    public Point2D getFarmVillage() {
        if(currentVillage + 1 > barbVillages.size() - 1){
            currentVillage = 0;
        } else {
            currentVillage++;
        }
        return barbVillages.get(currentVillage);
    }
}
