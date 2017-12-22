package sample;

import java.util.Timer;
import java.util.TimerTask;

public class Brain implements Data{
    boolean botState;
    Barbarian barb;
    Building build;
    Attack attack;
    Timer timer;

    public Brain() {
        barb = new Barbarian();
        build = new Building();
        attack = new Attack();
        botState = false;
        timer = new Timer();
        data.botInterval = 2;
    }

    public void runLoop(){
        if(botState) {
            //timer.schedule(new ExecuteCommands(),data.botInterval * 1000);
            timer = new Timer();
            timer.scheduleAtFixedRate(new ExecuteCommands(), data.botInterval * 1000, 1000);
        } else {
            timer.cancel();
        }
    }

    public void updateState(boolean newState) {
        botState = newState;
    }

    class ExecuteCommands extends TimerTask {
        public void run() {
            attack.updateTroops();
        }
    }

    public void addVillage(int xTarget, int yTarget){
        barb.addVillage(xTarget, yTarget);
    }
}
