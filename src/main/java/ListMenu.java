import java.util.ArrayList;

public class ListMenu extends AppState {
    private GeneralView window;
    private OptionList choices;
    private StoreManager sm;

    public ListMenu(GeneralView screen,StoreManager storeManager){
        window=screen;
        ArrayList<String> opts=new ArrayList<String>();
        opts.add("Use default path");
        opts.add("Use custom path");
        choices=new OptionList(opts);
        sm=storeManager;
    }

    public AppState nextState() {
        int chosen=window.choose(choices);
        switch (chosen) {
            case 0:
                if (sm == null) {
                    sm = new StoreManager(Main.DefPath);
                }
                Viewer lister = new Viewer(Main.DefPath, sm);
                if (lister.size() > 0) {
                    window.show("At the moment you have stored " + lister.size() + " passwords in your vault");
                    String site = window.ask("Enter site needed");
                    window.show("Your password is:"+lister.get(site));
                    return new MainMenu(window);
                }
                else{
                    window.show("There are no stored passwords at the moment");
                    return new MainMenu(window);
                }
            case 1:
                String path=window.ask("Input custom path");
                if(sm!=null){
                    if(sm.getStoragepath().equals(path)){
                        lister= new Viewer(path,sm);
                    }
                    else {
                        sm=new StoreManager(path);
                        lister= new Viewer(path,sm);
                    }
                }
                else {
                    sm= new StoreManager(path);
                    lister= new Viewer(path,sm);
                }
                if (lister.size() > 0) {
                    window.show("At the moment you have stored " + lister.size() + " passwords in your vault");
                    String site = window.ask("Enter site needed");
                    window.show("Your password is:"+lister.get(site));
                    return new MainMenu(window);
                }
                else{
                    window.show("There are no stored passwords at the moment");
                    return new MainMenu(window);
                }
            default:
                return new MainMenu(window);
        }
    }

    public String currentState() {
        return "List";
    }

    public int getid() {
        return 3;
    }
}
