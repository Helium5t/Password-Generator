
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
        switch (args[0].charAt(0)){
            case 'c':
                window=new CommandLine();
            case 'g':
                window= new Graphics();
                default:
                    window=new CommandLine();
        }
        boolean end = false;
        while(!end) {
            ArrayList<String> opts = new ArrayList<String>(2);
            opts.add("Generate new password");
            opts.add("See password list");
            OptionList choices = new OptionList(opts);
            int chosen = window.choose(choices);
            switch (chosen) {
                case 0:
                    Integer wlen = Integer.decode(window.ask("Choose word length"));
                    Integer digits = Integer.decode(window.ask("Choose number of digits"));
                    Generator gen=new Generator(wlen,digits);
                    Password pw=gen.generate();
                    String password=pw.getPw();
                    window.show("Your password is:" + password);
                    opts.clear();
                    opts.add("Save");
                    opts.add("Close");
                    choices=new OptionList(opts);
                    chosen = window.choose(choices);
                    switch(chosen){
                        case 0:
                            opts.clear();
                            opts.add("Use default path");
                            opts.add("Use custom path");
                            choices=new OptionList(opts);
                            chosen = window.choose(choices);
                            switch (chosen){
                                case 0:
                                    String path=DefPath;
                                    StoreManager sm=new StoreManager(path);
                                    sm.add(pw);
                                    break;
                                case 1:
                                    path=window.getPath();
                                    sm=new StoreManager(path);
                                    sm.add(pw);
                                    break;
                            }
                            break;
                        case 1:
                            Toolkit tk=Toolkit.getDefaultToolkit();
                            Clipboard cb=tk.getSystemClipboard();
                            StringSelection ss=new StringSelection(password);
                            cb.setContents(ss,ss);
                            window.show("Password saved to clipoard");
                            end=true;
                            break;
                    }
                    break;
                case 1:
                    //TODO: viewer;
                    break;
                case 2:
                    end = true;
                    break;
                default:
                    //TODO: viewer;
            }
        }

    }
}
//TODO: get rid of serr prints
//TODO: functional classes, full implementation of both views
//TODO: Save password, load password list
