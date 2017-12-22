package sample;

public class Brain {
    boolean botState;

    public Brain() {
        botState = false;
    }

    public void runLoop(){
        while(botState) {

        }
    }

    public void updateState(boolean newState) {
        botState = newState;
    }
}
