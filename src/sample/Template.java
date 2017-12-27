package sample;

import com.jfoenix.controls.JFXButton;

import java.util.LinkedList;

public class Template {

    JFXButton boundButton;
    public String templateName;
    public LinkedList<Integer> troops;

    public Template() {
    }

    public Template(String templateName, LinkedList<Integer> troops) {
        this.templateName = templateName;
        this.troops = troops;
    }

    public void updateButton(JFXButton button) {
        this.boundButton = button;
    }

    public void updateTroops(LinkedList<Integer> troops) {
        this.troops = troops;
    }

    public void updateTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public JFXButton getBoundButton() {
        return boundButton;
    }

    public String getTemplateName() {
        return templateName;
    }

    public LinkedList<Integer> getTroops() {
        return troops;
    }
}
