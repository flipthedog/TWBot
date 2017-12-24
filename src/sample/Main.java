package sample;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.LinkedList;


public class Main extends Application {

    public static Scene mainStage;
    public static Stage primaryStage;
    public static Stage popUp;

    private static Scene troopTemplateScene;

    public static MainController mainScreenController = new MainController();
    public static TroopPresetController troopPresetController = new TroopPresetController();


    @Override
    public void start(Stage primaryStage) throws Exception{

        /**
         * Setting up a scene to allow adding and removing of farming templates
         */
        FXMLLoader troopTemplateLoad = new FXMLLoader(getClass().getResource("TroopPreset.fxml"));
        Parent troopTemplate = troopTemplateLoad.load();
        troopPresetController = troopTemplateLoad.getController();
        troopPresetController.setMainController(this);
        troopTemplateScene = new Scene(troopTemplate);

        /**
         * Setting up main screen scene
         */
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

    public void setMainScreen() {
        popUp.close();
    }

    public void setTroopTemplate(JFXButton butt, LinkedList<Integer> troops) {
        popUp = new Stage();
        popUp.setScene(troopTemplateScene);
        popUp.setTitle("Troop Farming Template");
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.initOwner(butt.getScene().getWindow());
        popUp.showAndWait();
    }

    public static void main(String[] args) throws SQLException {
        MainController cont = new MainController();

        //Database.addVillage(300,500);
        //Database.addVillage(400,200);
        //Database.addVillage(500,300);
        //System.out.println("Total rows: " + Database.getRowCount());
        //System.out.println(Database.getVillage(0));
        //System.out.println(Database.getVillage(1));
        //System.out.println(Database.getVillage(2));
        //Database.initDatabase();

       /* LinkedList<Integer> troops = new LinkedList<>();
        for (int i = 0; i < 9; i ++) {
            troops.add(0);
        }
        System.out.println("This is troops: " + troops);
        Database.addTemplate(troops,"YOLOLO");
        System.out.println("This is the row length: " + Database.getTemplateRowCount());*/
        launch(args);
//        Database base = new Database();
     //   Database.createTable();
    }

}
