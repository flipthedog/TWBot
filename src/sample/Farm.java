package sample;

import org.openqa.selenium.By;

public class Farm implements Data{
    int usedPopulation;
    int maxPopulation;

    public Farm() {
        System.out.println("Initialized farm");
        updatePopulation();
    }

    /**
     * Update the current population values based on the overview screen
     */
    public void updatePopulation() {
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

    public int getMaxPopulation() {
        updatePopulation();
        return maxPopulation;
    }

    public int getUsedPopulation() {
        updatePopulation();
        return usedPopulation;
    }
}
