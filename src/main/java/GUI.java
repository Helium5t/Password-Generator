import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Region;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GUI extends Application{
    private static Group root;
    private static ObservableList nodes;
    private static int choiceid;
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TESTING WINDOW");
        root= new Group();
        nodes=root.getChildren();
        Text hello=new Text("Welcome to Password generator".toUpperCase());
        hello.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC,25));
        hello.setTranslateY(225);
        hello.setTranslateX(1);
        primaryStage.setTitle("Password Generator");
        nodes.add(hello);
        Scene shown=new Scene(root,500,500);
        primaryStage.setScene(shown);
        primaryStage.show();
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

    public static void show(String message) {
    }

    public static int choose(OptionList choices) {
        final ChoiceBox cb=new ChoiceBox();
        ObservableList opts=cb.getItems();
        for(int i=0;i<choices.size();i++){
            opts.add(choices.getContents().get(i));
        }
        Button okb=new Button("Choose");
        root=new Group();
        nodes=root.getChildren();
        nodes.add(cb);
        nodes.add(okb);
        EventHandler<MouseEvent> chosen=new EventHandler (){
            public void handle(Event event) {
                GUI.setchoice(cb.getSelectionModel().getSelectedIndex());
            }
        };
        okb.addEventHandler(MouseEvent.MOUSE_CLICKED,chosen);
        return choiceid;
    }

    public static String ask(String message) {
        return null;
    }

    public static String getPath() {
        return null;
    }
    public static void setchoice(int id){
        choiceid=id;
    }
    public static void build(){
        launch();
    }
}
