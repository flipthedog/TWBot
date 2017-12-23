package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.util.LinkedList;

public class TroopPresetController {
    private Main mainController;

    @FXML
    private JFXTextField spears;
    @FXML
    private JFXTextField swords;
    @FXML
    private JFXTextField axes;
    @FXML
    private JFXTextField scouts;
    @FXML
    private JFXTextField lightCavalry;
    @FXML
    private JFXTextField heavyCavalry;
    @FXML
    private JFXTextField nobles;
    @FXML
    private JFXTextField rams;
    @FXML
    private JFXTextField cats;
    @FXML
    private JFXTextField paladin;

    public TroopPresetController() {

    }

    public void setMainController(Main main){ this.mainController = main;}

    public void setFields(LinkedList<Integer> troops) {
        spears.setText(troops.get(0).toString());
        swords.setText(troops.get(1).toString());
        axes.setText(troops.get(2).toString());
        scouts.setText(troops.get(3).toString());
        lightCavalry.setText(troops.get(4).toString());
        heavyCavalry.setText(troops.get(5).toString());
        rams.setText(troops.get(6).toString());
        cats.setText(troops.get(7).toString());
        nobles.setText("0");
        paladin.setText(troops.get(9).toString());
    }

    public void clearFields() {
        spears.clear();
        swords.clear();
        axes.clear();
        scouts.clear();
        lightCavalry.clear();
        heavyCavalry.clear();
        rams.clear();
        cats.clear();
        paladin.clear();
    }

    @FXML
    public void backToMain() {
        mainController.setMainScreen();
    }

    @FXML
    public void createTemplate() {

    }

    public void removeTemplate() {

    }
}
