public class End extends AppState {
    private String state;

    public End(){
        state="End";
    }
    public AppState nextState() {
        return this;
    }

    public String currentState() {
        return state;
    }
}
