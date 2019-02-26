public class Launch extends AppState {
    private String name;

    public Launch(){
        name="Launch";
    }
    public AppState nextState() {
        return this;
    }

    public String currentState() {
        return name;
    }

    public int getid() {
        return -1;
    }
}
