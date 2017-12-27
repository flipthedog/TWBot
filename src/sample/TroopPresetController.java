package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;


public class TroopPresetController implements Data{
    private Main mainController;

    @FXML
    private Label templateStatus;
    @FXML
    private JFXTextField templateName;
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

    public void setFields(LinkedList<Integer> troops, String templateName) {
        this.templateName.setText(templateName);
        spears.setText(troops.get(0).toString());
        swords.setText(troops.get(1).toString());
        axes.setText(troops.get(2).toString());
        scouts.setText(troops.get(3).toString());
        lightCavalry.setText(troops.get(4).toString());
        heavyCavalry.setText(troops.get(5).toString());
        rams.setText(troops.get(6).toString());
        cats.setText(troops.get(7).toString());
        //nobles.setText("0");
        paladin.setText(troops.get(8).toString());
    }

    public void clearFields() {
        templateName.clear();
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
        LinkedList<Integer> userTemplate = createTemplateFromFields();
        Database.addTemplate(userTemplate,templateName.getText());
        templateStatus.setText("Added template: " + templateName.getText());

        Template newTemplate = new Template(templateName.getText(),userTemplate);
        mainController.getMainScreenController().addTemplateDisplay(newTemplate);
        clearFields();
        mainController.setMainScreen();
    }

    private LinkedList<Integer> createTemplateFromFields() {
        LinkedList<Integer> returnList = new LinkedList<>();

        returnList.add(Integer.parseInt(spears.getText().trim()));
        returnList.add(Integer.parseInt(swords.getText().trim()));
        returnList.add(Integer.parseInt(axes.getText().trim()));
        returnList.add(Integer.parseInt(scouts.getText().trim()));
        returnList.add(Integer.parseInt(lightCavalry.getText().trim()));
        returnList.add(Integer.parseInt(heavyCavalry.getText().trim()));
        returnList.add(Integer.parseInt(rams.getText().trim()));
        returnList.add(Integer.parseInt(cats.getText().trim()));
        returnList.add(Integer.parseInt(paladin.getText().trim()));

        return returnList;
    }

    @FXML
    public void removeTemplate() {
        Database.removeTemplate(templateName.getText());
        templateStatus.setText("Removed template: " + templateName.getText());
        mainController.getMainScreenController().updateTemplateDisplay();
        clearFields();
        mainController.setMainScreen();
    }



}
