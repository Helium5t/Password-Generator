import java.util.ArrayList;

public class SaveMenu extends AppState {
    private OptionList choices;
    private GeneralView window;
    private Password pw;

    public SaveMenu(GeneralView screen,Password password){
        window=screen;
        ArrayList<String> opts=new ArrayList<String>();
        opts.add("Use default path");
        opts.add("Use custom path");
        choices=new OptionList(opts);
        pw=password;
    }
    public AppState nextState() {
        StoreManager sm=null;
        int chosen=window.choose(choices);
        switch (chosen){
            case 0:
                sm=new StoreManager(Main.DefPath);
                sm.add(pw);
                break;
            case 1:
                String path=window.getPath();
                sm=new StoreManager(path);
                sm.add(pw);
                break;
        }
        sm.save();
        return new MainMenu(window,sm);
    }

    public String currentState() {
        return "Save";
    }

}
