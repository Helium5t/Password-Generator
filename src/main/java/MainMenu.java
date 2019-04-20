import java.util.ArrayList;

public class MainMenu extends AppState {
    private OptionList choices;
    private GeneralView window;
    private StoreManager sm;

    public MainMenu(GeneralView screen){
        ArrayList<String> opts=new ArrayList<String>();
        opts.add("Generate new password");
        opts.add("See password list");
        opts.add("End");
        choices=new OptionList(opts);
        window=screen;
        sm=null;
    }

    public MainMenu(GeneralView screen,StoreManager storeManager){
        ArrayList<String> opts=new ArrayList<String>();
        opts.add("Generate new password");
        opts.add("See password list");
        opts.add("End");
        choices=new OptionList(opts);
        window=screen;
        sm=storeManager;
    }

    public AppState nextState() {
        int chosen=window.choose(choices);
        switch (chosen){
            case 0:
                return new GenerationMenu(window);
            case 1:
                return new ListMenu(window,sm);
            case 2:
                return new End();
            default:
                window.show("Command was not recognized, please choose another option");
                return this;
        }
    }

    public String currentState() {
        return "Main Menu";
    }

    public int getid() {
        return 1;
    }
}
