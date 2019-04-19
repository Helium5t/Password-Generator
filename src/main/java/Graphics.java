import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


@Deprecated

public class Graphics implements GeneralView{
    private StateObserver mainobs;
    private boolean wait;
    public Graphics(AppState state){
        StateObserver so=new StateObserver(state);
        Thread Observer=new Thread(so);
        Observer.start();
        GUI.newGUI(so);
        Thread screen=new Thread(new GUILauncher());
        screen.start();
        System.out.println("OUT OF LAUNCHER");
        mainobs=so;
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
}
