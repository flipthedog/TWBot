package sample;

import org.openqa.selenium.By;

public class Resources implements Data{
    int wood;
    int stone;
    int iron;
    int warehouseSize;

    public Resources() {
        updateResources();
        updateWarehouseSize();
    }

    /**
     * Update the resources based on the values on the overview screen
     */
    public void updateResources() {
        Building.goToScreen("overview");
        this.wood = Integer.parseInt(data.driver.findElement(By.id("wood")).getText());
        this.stone = Integer.parseInt(data.driver.findElement(By.id("stone")).getText());
        this.iron = Integer.parseInt(data.driver.findElement(By.id("iron")).getText());
    }

    /**
     * Update the current warehouse size based on the values on the overview screen
     */
    public void updateWarehouseSize(){
        Building.goToScreen("overview");
        this.warehouseSize = Integer.parseInt(data.driver.findElement(By.id("storage")).getText());
    }

    public int getWood() {
        return this.wood;
    }

    public int getIron() {
        return iron;
    }

    public int getStone() {
        return stone;
    }

    public int getWarehouseSize() {
        return warehouseSize;
    }

    /**
     * Return all resources as an array
     * @return
     */
    public int[] getResources() {
        int returnArr[] = {this.wood,this.stone,this.iron};
        return returnArr;
    }

    public boolean isWarehouseBig() {
        updateWarehouseSize();
        updateResources();
        if( wood / warehouseSize > 0.8) {
            return false;
        } else if ( stone / warehouseSize > 0.8) {
            return false;
        } else if ( iron / warehouseSize > 0.8) {
            return false;
        }
        return true;
    }
}
