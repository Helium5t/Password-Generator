
public class Main {
    public static final String DefPath="Vault.pw";
    private static AppState state;
    public static void main(String[] args){
        GeneralView window;
        state=new Launch();
        AppObserver observer=new AppObserver();
        switch (args[0].charAt(0)) {
            case 'c':
                window = new CommandLine();
                break;
            case 'g':
                window=new GraphicsInterface(observer);
                break;
            default:
                window = new CommandLine();
                break;
        }
        boolean end = false;
        StoreManager sm=null;
        state=new MainMenu(window);
        while(!state.currentState().equals("End")) {
            state=state.nextState();
        }

    }

    public static AppState getState() {
        return state;
    }
}

//TODO: full implementation of both views
