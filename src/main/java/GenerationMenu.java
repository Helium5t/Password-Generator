import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;



public class GenerationMenu extends AppState {
    private OptionList choices;
    private GeneralView window;

    public GenerationMenu(GeneralView screen){
        window=screen;
        ArrayList<String> opts=new ArrayList<String>();
        opts.add("Save");
        opts.add("Close");
        choices=new OptionList(opts);
    }
    public AppState nextState() {
        Integer wlen = Integer.decode(window.ask("Choose word length"));
        System.out.println(wlen);
        Integer digits = Integer.decode(window.ask("Choose number of digits"));
        System.out.println(digits);
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
        int chosen=window.choose(choices);
        switch (chosen){
            case 0:
                return new SaveMenu(window,pw);
            case 1:
                Toolkit tk=Toolkit.getDefaultToolkit();
                Clipboard cb=tk.getSystemClipboard();
                StringSelection ss=new StringSelection(password);
                cb.setContents(ss,ss);
                window.show("Password saved to clipoard");
                return new End();
            default:
                tk=Toolkit.getDefaultToolkit();
                cb=tk.getSystemClipboard();
                ss=new StringSelection(password);
                cb.setContents(ss,ss);
                window.show("Password saved to clipoard");
                return new End();
        }
    }

    public String currentState() {
        return "Generator";
    }

    public int getid() {
        return 2;
    }
}
