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

import java.util.LinkedList;
import java.util.List;


public class MainController implements Data{
    Brain brain;

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

        JFXButton sadButton = new JFXButton();
        sadButton.setText("LOL");
        templateHolder2.getChildren().add(sadButton);
    }

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

    public void checkDailyQuest() {
        try {
            driver.findElement(By.xpath("//a[text()='Open']")).click();
        } catch (NoSuchElementException e){
            System.out.println("There is no open daily quest");
        }
        data.driver.findElement(By.className("desktop")).sendKeys("v");
        data.homeURL = data.driver.getCurrentUrl().replaceAll("overview", "");
        brain = new Brain(this.status);
    }

    public void addVillage(){
        brain.addVillage(Integer.parseInt(barbarianX.getText()),Integer.parseInt(barbarianY.getText()));
        barbarianX.clear();
        barbarianY.clear();
        setStatus("Added a barbarian village");
    }

    @FXML
    public void toggleBot(){
        brain.updateState(startStop.isSelected());
        brain.runLoop();
    }

    @FXML
    public void updateMinimumTroop() {
        data.minimumTroopCount = Integer.parseInt(minimumTroop.getText());
    }

    @FXML
    public void updateBotInterval() {
        data.botInterval = Integer.parseInt(botInterval.getText());
    }

    @FXML
    public void templateScreen() {
        mainController.setTroopTemplate(templateButton);
    }

    public void addTemplateDisplay(LinkedList<Integer> troops, String templateName) {

        JFXButton activeTemplateButton = new JFXButton();
        activeTemplateButton.setText(templateName);
        activeTemplateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainController.setExistingTroopTemplate(activeTemplateButton, troops, templateName);
            }
        });

        templateHolder2.getChildren().add(activeTemplateButton);
    }

    public void removeTemplateDisplay(String templateName) {
        ObservableList<Node> list = templateHolder2.getChildren();
        Node theNodeWeAreLookingFor = null;
        for(Node node: list) {
            if(node.equals(JFXButton))
            if(node.getText().equals("templateName")){
                theNodeWeAreLookingFor = node;
            }
        }
        list.removeAll(theNodeWeAreLookingFor);
        System.out.println("This is the node that was removed: " + theNodeWeAreLookingFor.getAccessibleText());
    }

    public void updateTemplateDisplay() {
        templateHolder2.getChildren().clear();

        LinkedList<LinkedList<Integer>> list = Database.getAllTroopTemplates();

        for(LinkedList<Integer> subList: list) {
            addTemplateDisplay(subList.subList(1,subList.size()-1), subList.get(0));
        }
    }

    public VBox getTemplateHolder() {
        return this.templateHolder2;
    }

}
