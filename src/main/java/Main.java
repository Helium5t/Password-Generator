
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final String DefPath="Vault.pw";
    public static void main(String[] args){
        GeneralView window;
        switch (args[0].charAt(0)) {
            case 'c':
                window = new CommandLine();
                break;
            case 'g':
                window=new Graphics();
                break;
            default:
                window = new CommandLine();
                break;
        }
        boolean end = false;
        StoreManager sm=null;
        AppState state=new MainMenu(window);
        while(!state.currentState().equals("End")) {
            state=state.nextState();
        }

    }
}

//TODO: full implementation of both views
