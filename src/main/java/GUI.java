import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Region;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Deprecated
public class GUI extends Application{
    private static StateObserver mainobs;
    private static String current;

    // control vars
    private static int choiceid;
    private static String wlen;
    private static String digits;
    private static String site;
    private static String response;

    public static void newGUI(StateObserver so){
        mainobs=so;
        current=mainobs.getStateName();
        response="";
        site="";
        digits="";
        wlen="";
        choiceid=-1;
    }

    public static void main(String[] args){
        new Thread(new GUILauncher()).start();
        System.out.println("OUT OF LAUNCH");
    }

    public void reset(){
        if(!current.equals(mainobs.getStateName())){
            wlen="";
            digits="";
            site="";
            response="";
            choiceid=-1;
            current=mainobs.getStateName();
        }
    }

    public synchronized void start(Stage primaryStage){
        Text welcome= new Text("WELCOME TO PASSWORD GENERATOR");
        welcome.setFont(Font.font("Sitka",FontWeight.BOLD,50));
        welcome.setTranslateX(0);
        welcome.setTranslateY(250);
        Stage shown=new Stage();
        Group tree=new Group();
        ObservableList leaves=tree.getChildren();
        leaves.add(welcome);
        Scene contents=new Scene(tree,500,500);
        shown.setScene(contents);
        while (!mainobs.getStateName().equals("End")) {
            create(shown);
            reset();
            try {
                contents.setRoot(SceneBuilder.getNode(mainobs.getStateid()));
            }
            catch (NullPointerException e){}
        }
    /*
        ChoiceBox cb = new ChoiceBox();
        ObservableList choices= cb.getItems();
        choices.add("Choice 1");
        choices.add("Choice 2");
        nodes.add(cb);
        cb.setTranslateY(250);
        cb.setTranslateX(150);
        cb.setPrefWidth(200);
        Scene shown= new Scene(root,500,500);
        primaryStage.setScene(shown);
        primaryStage.show();
        Button b=new Button("OK");
        b.setTranslateX(cb.getTranslateX()+ cb.getWidth() + 50);
        b.setTranslateY(250);
        nodes.add(b);
        */
    }

    private static void create(Stage shown){
        shown.show();
    }

    public static void show(String message) {
        System.out.println(message);
    }

    public static synchronized int choose(OptionList choices){
        int toret=choiceid;
        while (toret==-1){
            toret=choiceid;
            try{
                synchronized (mainobs){
                    mainobs.wait();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        choiceid=-1;
        return toret;
    }

    public static String ask(String message) {
        String returned=response;
        while (returned.equals("")){
            try{
                synchronized (mainobs){
                    mainobs.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            returned=response;
        }
        response="";
        return returned;
    }

    public static String getPath() {
        return null;
    }

    public static int getChoice() {
        return choiceid;
    }

    public static String getResponse() {
        return response;
    }

    public static void setchoice(int id){
        choiceid=id;
    }
    public static void build(){
        launch();
    }


    public static void setDigits(String digits) {
        GUI.digits = digits;
    }
    public static void setSite(String site) {
        GUI.site = site;
    }
    public static void setWlen(String wlen) {
        GUI.wlen = wlen;
    }
}
