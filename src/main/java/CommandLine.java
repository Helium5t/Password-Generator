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
        System.out.println(choices.getContents().get(0) + " - " + choices.getContents().get(1));
        System.out.println("Write 0 or 1 and press Enter");
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
