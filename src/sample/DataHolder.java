package sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DataHolder implements Data{
    /**
     * Webdriver holding the current browser web page
     */
    public WebDriver driver;

    /**
     * URL string unique to the user, used to go to buildings
     */
    public String homeURL;

    /**
     * Interval in seconds between bot loops
     */
    public int botInterval;

    /**
     * The current minimum troop farm count
     */
    public int minimumTroopCount;
}
