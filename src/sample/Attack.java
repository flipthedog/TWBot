package sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

public class Attack implements Data {
    LinkedList<Integer> stationedTroops;

    public Attack() {
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
        return updatedTroops;
    }
}
