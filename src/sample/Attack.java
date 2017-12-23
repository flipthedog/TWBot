package sample;

import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

public class Attack implements Data {
    LinkedList<Integer> stationedTroops;

    public Attack() {
        System.out.println("Initialized attack");
        stationedTroops = new LinkedList<>();
    }

    /**
     * Send an attack to a village
     *
     * @param troops
     * @param xTarget
     * @param yTarget
     */
    public static void sendAttack(LinkedList<Integer> troops, int xTarget, int yTarget) {
        Building.goToScreen("place");

        List<WebElement> enter = data.driver.findElements(By.className("unitsInput"));

        for (int i = 0; i < troops.size(); i++) {
            enter.get(i).sendKeys(troops.get(i).toString());
        }

        data.driver.findElement(By.className("target-input-field")).sendKeys(Integer.toString(xTarget) + "|" + Integer.toString(yTarget));
        data.driver.findElement(By.id("target_attack")).click();
        Building.goToScreen("overview");
    }

    /**
     * Send a support to a village
     *
     * @param troops
     * @param xTarget
     * @param yTarget
     */
    public static void sendSupport(LinkedList<Integer> troops, int xTarget, int yTarget) {
        Building.goToScreen("place");

        List<WebElement> enter = data.driver.findElements(By.className("unitsInput"));

        for (int i = 0; i < troops.size(); i++) {
            enter.get(i).sendKeys(troops.get(i).toString());
        }

        data.driver.findElement(By.className("target-input-field")).sendKeys(Integer.toString(xTarget) + "|" + Integer.toString(yTarget));
        data.driver.findElement(By.id("target_support")).click();
        Building.goToScreen("overview");
    }

    public LinkedList<Integer> updateTroops(){
        Building.goToScreen("place");
        List<WebElement> troops = data.driver.findElements(By.className("units-entry-all"));
        LinkedList<Integer> updatedTroops = new LinkedList<>();
        for (WebElement e : troops) {
            updatedTroops.add(Integer.parseInt(e.getText().replaceAll("[(]", "").replaceAll("[)]", "")));
        }
        this.stationedTroops = updatedTroops;
        Building.goToScreen("overview");
        return updatedTroops;
    }

    /**
     * Choose the farm troops to be sent based on the current max population
     * @return Linked list of troops to be sent on farm trip
     */
    public LinkedList<Integer> chooseFarmTroops() {
        updateTroops(); // update the current troops available

        LinkedList<Integer> farmTroops = new LinkedList<>();
        LinkedList<Integer> blankTroops = new LinkedList<>();

        for(int i = 0; i < stationedTroops.size(); i++){
            farmTroops.add(0);
            blankTroops.add(0);
        }

        int spearMan = stationedTroops.get(0);
        int swordMan = stationedTroops.get(1);
        int axeMan = stationedTroops.get(2);

        int LC = stationedTroops.get(4);
        int HC = stationedTroops.get(5);

        int paladin = stationedTroops.get(8);

        if(paladin != 0) {
            farmTroops.add(8,1);
        }

        if(spearMan > data.minimumTroopCount && swordMan > data.minimumTroopCount){
            farmTroops.add(0,data.minimumTroopCount);
            farmTroops.add(1,data.minimumTroopCount);
            return farmTroops;
        } else if (spearMan > data.minimumTroopCount && axeMan > data.minimumTroopCount) {
            farmTroops.add(0,data.minimumTroopCount);
            farmTroops.add(2,data.minimumTroopCount);
            return farmTroops;
        } else if (swordMan > data.minimumTroopCount && axeMan > data.minimumTroopCount) {
            farmTroops.add(1,data.minimumTroopCount);
            farmTroops.add(2,data.minimumTroopCount);
            return farmTroops;
        }

        if(farmTroops.equals(blankTroops)){
            return null;
        } else {
            return farmTroops;
        }
    }

}
