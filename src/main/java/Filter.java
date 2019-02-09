import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;



public class Filter {
    private static final String FDPATH = "C:\\Users\\Helium\\IdeaProjects\\PWGEN\\src\\main\\resources\\FullDictionary.txt";
    private static final String FPATH="C:\\Users\\Helium\\IdeaProjects\\PWGEN\\src\\main\\resources\\Dictionary.txt";
    public static void main(String[] args){
        try {
            FileInputStream fin= new FileInputStream(FDPATH);
            Scanner sin=new Scanner(fin);
            PrintWriter out=new PrintWriter(new FileOutputStream(FPATH));
            String word=new String();
            while(sin.hasNextLine()){
                word=sin.nextLine();
                if(word.length()>2 && !word.contains(".") && !(word.toUpperCase().equals(word)) && !word.contains("1") && !word.contains("2") &&
                        !word.contains("3") && !word.contains("4") && !word.contains("5") && !word.contains("6") && !word.contains("7") &&
                        !word.contains("8") && !word.contains("9") && !word.contains("0") && !word.contains("-") && !word.contains("&") && !word.contains("'")){
                    out.println(word);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> LenFilter(int length){
        try {
            FileInputStream fin=new FileInputStream(FPATH);
            ArrayList<String> filtered = new ArrayList<String>(65000);
            Scanner sin=new Scanner(fin);
            String word;
            while (sin.hasNextLine()){
                word=sin.nextLine();
                if(word.length()==length){
                    filtered.add(word);
                }
            }
            return filtered;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>(0);
    }
}
