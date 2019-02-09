import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Graphics extends Application implements GeneralView{


    public void start(Stage primaryStage) throws Exception {

    }
    public static void main(String[] args ){
        launch(args);
    }
    public void show(String message) {
    }

    public int choose(OptionList choices) {
        return 0;
    }

    public String ask(String message) {
        return null;
    }
}
