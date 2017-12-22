package sample;

import org.openqa.selenium.By;

public class Farm implements Data{
    int usedPopulation;
    int maxPopulation;

    public Farm() {
        updatePopulation();
    }

    /**
     * Update the current population values based on the overview screen
     */
    public void updatePopulation() {
        Building.goToScreen("overview");
        this.usedPopulation = Integer.parseInt(data.driver.findElement(By.id("pop_current_label")).getText());
        this.maxPopulation = Integer.parseInt(data.driver.findElement(By.id("pop_max_label")).getText());
    }

    /**
     * Returns a boolean to see if there is enough farm space
     * @return
     */
    public boolean isFarmBig() {
        if(usedPopulation / maxPopulation > 0.8) {
            return false;
        } else {
            return true;
        }
    }

}
