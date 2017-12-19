package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static Scene mainStage;
    public static Stage primaryStage;

    public static MainController mainScreenController = new MainController();


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader mainLoad = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        Parent mainScreen = mainLoad.load();
        mainScreenController = mainLoad.getController();
        mainScreenController.setMainController(this);
        mainStage = new Scene(mainScreen);
        primaryStage.setTitle("Tribal Wars Bot");
        primaryStage.setResizable(true);
        primaryStage.setScene(mainStage);
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.centerOnScreen();
        primaryStage.show();
        mainScreenController.setStatus("Please enter your login information");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
