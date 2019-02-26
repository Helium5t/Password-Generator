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
        }
    }

    public String getStateName() {
        return StateName;
    }

    public void update(){
        StateName=state.currentState();
    }
}
