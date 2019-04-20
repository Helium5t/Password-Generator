public class AppObserver {
    private AppState appState;

    public AppObserver(){
        appState=Main.getState();
    }

    public int getStateID(){
        synchronized (this) {
            this.notify();
            return Main.getState().getid();
        }
    }

    public String getStateName(){
        synchronized (this) {
            this.notify();
            return Main.getState().currentState();
        }
    }
}