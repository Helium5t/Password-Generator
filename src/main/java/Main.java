
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
                System.out.println("OUT OF GRAPHICS");
                break;
            default:
                window = new CommandLine();
                break;
        }
        boolean end = false;
        StoreManager sm=null;
        state=new MainMenu(window);
        while(!state.currentState().equals("End")) {
            System.out.println("CURRENT STATE:"+ state.currentState());
            state=state.nextState();
            System.out.println("NEXT STATE");
        }

    }

    public static AppState getState() {
        return state;
    }
}

//TODO: full implementation of both views
