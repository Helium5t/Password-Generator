import java.util.Scanner;

public class CommandLine implements GeneralView {
    public CommandLine(){}

    public void show(String message) {
        System.out.println(message);
        System.out.println("Insert OK when you are done.");
        Scanner in=new Scanner(System.in);
        in.nextLine();
    }

    public int choose(OptionList choices) {
        System.out.println("What do you want to do?");
        String toprint="";
        for(int i=0;i<choices.getContents().size();i++){
            toprint=toprint+ "[" + choices.getContents().get(i) + "]   ";
        }
        System.out.println(toprint);
        toprint = "Write ";
        for (int i=0; i< choices.getContents().size();i++){
            toprint=toprint + i + " ";
        }
        toprint=toprint+"and press Enter";
        System.out.println(toprint);
        Scanner in= new Scanner(System.in);
        char choice= in.nextLine().charAt(0);
        return Character.getNumericValue(choice);

    }

    public String ask(String message) {
        System.out.println(message);
        Scanner in=new Scanner(System.in);
        String returned=in.nextLine();
        return returned;
    }

    public String getPath() {
        System.out.println("Please input vault path:");
        Scanner in=new Scanner(System.in);
        String path=in.nextLine();
        return path;
    }
}
