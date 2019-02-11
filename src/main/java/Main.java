import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
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
                    String password=gen.generate();
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
                                    String path=""
                            }
                    }

                case 1:
                    //TODO: viewer;
                case 2:
                    end = true;
                default:
                    //TODO: viewer;
            }
        }

    }
}
//TODO: ask to save, ask for path, password storing method
//TODO: functional classes, full implementation of both views
//TODO: Save password, load password list
