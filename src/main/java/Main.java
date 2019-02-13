
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
                    String site = window.ask("Input site related to password");
                    Generator gen;
                    Password pw;
                    if (site.equals("")){
                        gen = new Generator(wlen,digits);
                        pw=gen.generate();
                    }
                    else {
                        gen= new Generator(wlen,digits,site);
                        pw=gen.generate();
                    }
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
                                    StoreManager sm=new StoreManager(DefPath);
                                    sm.add(pw);
                                    break;
                                case 1:
                                    String path=window.getPath();
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
                    opts.clear();
                    opts.add("Use default path");
                    opts.add("Use custom path");
                    choices=new OptionList(opts);
                    chosen= window.choose(choices);
                    switch (chosen){
                        case 0:
                            Viewer lister=new Viewer(DefPath,window);
                            site=window.ask("Enter site needed");
                            window.show(lister.get(site));
                            break;
                        case 1:
                            String path=window.ask("Input custom path");
                            lister=new Viewer(path,window);
                            site=window.ask("Enter site needed");
                            window.show(lister.get(site));
                            break;
                    }
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
//TODO: fix lister flow, fix interaction storemanager/lister (maybe use only one class)
//TODO: full implementation of both views
