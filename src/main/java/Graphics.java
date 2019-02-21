import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Graphics implements GeneralView,Runnable{
    public Graphics(){
        GUI.build();
    }
    public void show(String message) {
        GUI.show(message);
    }

    public int choose(OptionList choices) {

        return GUI.choose(choices);
    }

    public String ask(String message) {
        return GUI.ask(message);
    }

    public String getPath() {
        return GUI.getPath();
    }

    public void run() {

    }
}
