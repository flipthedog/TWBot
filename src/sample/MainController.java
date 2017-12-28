package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.awt.image.ImageWatched;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;


public class MainController implements Data{
    Brain brain;

    @FXML
    private VBox villageHolder;
    @FXML
    private VBox templateHolder2;
    @FXML
    private JFXButton templateButton;
    @FXML
    private JFXTextField botInterval;
    @FXML
    private JFXTextField minimumTroop;
    @FXML
    private JFXToggleButton startStop;
    @FXML
    private Label status;
    @FXML
    private VBox leftVBox;
    @FXML
    private JFXButton startBot;
    @FXML
    private JFXTextField userField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXTextField barbarianX;
    @FXML
    private JFXTextField barbarianY;


    public WebDriver driver;

    public MainController() {

    }

    private Main mainController;

    public void setMainController(Main main){ this.mainController = main;}

    /**
     * Called at the start of the main controller initialization
     */
    public void initialize() {
        updateTemplateDisplay();
        updateVillageDisplay();
        brain = new Brain(this.status);
    }

    @FXML
    private void startBot(){
        setStatus("Starting Web Driver");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Floris\\Documents\\TribalWarsBot\\lib\\chromedriver.exe");
        driver = new ChromeDriver();
        data.driver = driver;
        driver.get("https://www.tribalwars.net");

        setStatus("Logging in");
        WebElement elUsername = driver.findElement(By.id("user"));
        WebElement elPassword = driver.findElement(By.id("password"));
        WebElement loginButt = driver.findElement(By.className("btn-login"));

        elUsername.sendKeys(userField.getText());
        elPassword.sendKeys(passwordField.getText());
        loginButt.click();
        listActiveWorlds();

    }

    /**
     * List all the active worlds the user just logged into
     */
    private void listActiveWorlds(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> openWorlds = driver.findElements(By.className("world_button_active"));
        for(WebElement e : openWorlds){
            JFXButton butt = new JFXButton();
            butt.setText(e.getText());
            butt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedWorld(butt);
                }
            });
            leftVBox.getChildren().add(butt);
        }
        setStatus("Please select your active world");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setStatus(String statusText){
        status.setText(statusText);
    }

    public void selectedWorld(JFXButton button){
        driver.findElement(By.xpath("//span[text()='" + button.getText() + "']")).click();
        setStatus("Entered " + button.getText());
        checkDailyQuest();
    }

    /**
     * Check if there is a daily quest, if there is open it
     */
    public void checkDailyQuest() {

        try {
            driver.findElement(By.xpath("//a[text()='Open']")).click();
        } catch (NoSuchElementException e){
            System.out.println("There is no open daily quest");
        }

        data.driver.findElement(By.className("desktop")).sendKeys("v");
        data.homeURL = data.driver.getCurrentUrl().replaceAll("overview", "");
        brain.delayedInitialize();
    }

    /**
     * Add a village to the barbarian village class
     */
    public void addVillage(){
        if(checkIfLegalVillage(Integer.parseInt(barbarianX.getText()), Integer.parseInt(barbarianY.getText()))) {
            brain.addVillage(Integer.parseInt(barbarianX.getText()), Integer.parseInt(barbarianY.getText()));
            barbarianX.clear();
            barbarianY.clear();
            setStatus("Added a barbarian village");
            updateVillageDisplay();
        }
    }

    /**
     * Remove the village from the barbarian village class
     */
    public void removeVillage() {
        if(checkIfLegalVillage(Integer.parseInt(barbarianX.getText()), Integer.parseInt(barbarianY.getText()))) {
            brain.removeVillage(Integer.parseInt(barbarianX.getText()), Integer.parseInt(barbarianY.getText()));
            barbarianX.clear();
            barbarianY.clear();
            setStatus("Removed a barbarian village");
            updateVillageDisplay();
        }
    }

    /**
     * Checks if the user input is a legal village
     * @param x
     * @param y
     * @return
     */
    public boolean checkIfLegalVillage(int x, int y) {
        String xStr = Integer.toString(x);
        String yStr = Integer.toString(y);

        if(xStr.length() > 3 || yStr.length() > 3) {
            setStatus("The x or y coord is too long");
            return false;
        } else if (xStr.contains(" ") || yStr.contains(" ")) {
            setStatus("No spaces!");
            return false;
        }

        return true;
    }

    /**
     * Toggle the bot on and off
     */
    public void toggleBot(){
        brain.updateState(startStop.isSelected());
        brain.runLoop();
    }

    /**
     * Update the minimum troop count
     */
    @FXML
    public void updateMinimumTroop() {
        data.minimumTroopCount = Integer.parseInt(minimumTroop.getText());
    }

    /**
     * Update the bot interval
     */
    @FXML
    public void updateBotInterval() {
        data.botInterval = Integer.parseInt(botInterval.getText());
    }

    /**
     * Update the template screen
     */
    @FXML
    public void templateScreen() {
        mainController.setTroopTemplate(templateButton);
    }

    /**
     * Add a template to the VBox display
     * @param temp
     */
    public void addTemplateDisplay(Template temp) {
        String templateName = temp.getTemplateName();
        LinkedList<Integer> troops = temp.getTroops();

        JFXButton activeTemplateButton = new JFXButton();
        activeTemplateButton.setText(templateName);
        activeTemplateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.setExistingTroopTemplate(activeTemplateButton, troops, templateName);
            }
        });

        temp.updateButton(activeTemplateButton);
        templateHolder2.getChildren().add(activeTemplateButton);
    }

    /**
     * This updates the VBox holding all the currently active templates
     */
    public void updateTemplateDisplay() {
        templateHolder2.getChildren().clear();

        LinkedList<Template> list = Database.getAllTroopTemplates();

        for(Template temp: list) {
            addTemplateDisplay(temp);
        }
    }

    /**
     * Update the VBox holding all the villages that are currently farmed
     */
    public void updateVillageDisplay() {
        villageHolder.getChildren().clear();

        LinkedList<Point2D> list = Database.getAllBarbarians();

        for(Point2D point: list) {
            addButtonDisplay(point);
        }
    }

    /**
     * Add a button to the village VBox
     * @param point The point that needs to be added
     */
    public void addButtonDisplay(Point2D point) {
        Label lbl = new Label();
        lbl.setText("(" + (int)point.getX() + "," + (int)point.getY() + ")");
        villageHolder.getChildren().add(lbl);
    }

}
