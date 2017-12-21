package sample;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Barbarian {
    LinkedList<Point2D> barbVillages;
    int currentVillage;

    public Barbarian() {
        barbVillages = new LinkedList<>();
        currentVillage = 0;
    }

    /**
     * Add a village to regularly farm
     * @param xCoord
     * @param yCoord
     */
    public void addVillage(int xCoord, int yCoord) {
        barbVillages.add(new Point(xCoord,yCoord));
    }

    /**
     * Get the next farm village
     * @return
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
