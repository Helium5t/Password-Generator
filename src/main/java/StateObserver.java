
@Deprecated
public class StateObserver implements Runnable{
    private AppState state;
    private String StateName;

    public StateObserver(AppState observed){
        state=observed;
        StateName=state.currentState();
    }

    public void run() {
        while(!StateName.equals("End")){
            update();
            synchronized (this) {
                try {
                    while (StateName.equals(state.currentState())) {
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.notify();
        }
    }

    public String getStateName() {
        check();
        return StateName;
    }
    public int getStateid(){return state.getid();}

    public void check(){
        if(!StateName.equals(state.currentState())){
            StateName=state.currentState();
        }
    }

    public void update(){
    }
}
