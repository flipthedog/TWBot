package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class MainController {

    public WebDriver driver;

    @FXML
    private JFXButton startBot;
    @FXML
    private JFXTextField userField;
    @FXML
    private JFXPasswordField passwordField;

    public MainController() {

    }

    private Main mainController;

    public void setMainController(Main main){ this.mainController = main;}

    @FXML
    private void startBot(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Floris\\Documents\\TribalWarsBot\\lib\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.tribalwars.net");

        WebElement elUsername = driver.findElement(By.id("user"));
        WebElement elPassword = driver.findElement(By.id("password"));
        WebElement loginButt = driver.findElement(By.className("btn-login"));

        elUsername.sendKeys(userField.getText());
        elPassword.sendKeys(passwordField.getText());
        loginButt.click();
    }
}
