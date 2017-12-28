package sample;

import javafx.scene.control.Label;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Brain implements Data{
    boolean botState;
    Barbarian barb;
    Building build;
    Attack attack;
    Timer timer;
    Farm farm;
    Label status;

    public Brain(Label status) {
        barb = new Barbarian();
        build = new Building();
        attack = new Attack();

        botState = false;
        timer = new Timer();
        data.botInterval = 2;
        this.status = status;
    }

    /**
     * Only called once the main village screen is actually available
     */
    public void delayedInitialize() {
        farm = new Farm();
    }

    /**
     * Run the main loop of the bot
     */
    public void runLoop(){
        if(botState) {
            //timer.schedule(new ExecuteCommands(),data.botInterval * 1000);
            timer = new Timer();
            timer.scheduleAtFixedRate(new ExecuteCommands(), data.botInterval * 100, 1000);
        } else {
            timer.cancel();
        }
    }

    /**
     * Update whether the bot is on or off
     * @param newState state of the bot (On == true)
     */
    public void updateState(boolean newState) {
        botState = newState;
        if(botState){
            setStatus("Enabled bot");
        } else {
            setStatus("Disabled bot");
        }
    }

    class ExecuteCommands extends TimerTask {
        public void run() {
            attack.updateTroops();
            farm();
        }
    }

    public void addVillage(int xTarget, int yTarget){
        barb.addVillage(xTarget, yTarget);
    }

    public void removeVillage(int xTarget, int yTarget) {
        barb.removeVillage(xTarget,yTarget);
    }

    /**
     * Send out a farm attack, called on an interval
     */
    public void farm() {
        setStatus("Sending a farm attack");
        LinkedList<Integer> farmTroops = attack.chooseTemplateFarmTroops();
        LinkedList<Integer> blankTroops = new LinkedList<>();

        for(int i = 0; i < 9; i++) {
            blankTroops.add(0);
        }

        if(!farmTroops.equals(blankTroops)) {

            Point2D barbVill = barb.getFarmVillage();
            Attack.sendAttack(farmTroops,(int)barbVill.getX(),(int)barbVill.getY());
            System.out.println("!!!!!!!!!!!I am attempting to farm!!!!!!!");
            System.out.println("These are the troops: " + farmTroops);
            System.out.println("This is the village: " + barbVill + "\n\n\n");

        } else {
            System.out.println("No troop template");
        }

    }

    public void setStatus(String status) {
        //this.status.setText(status);
    }

}
