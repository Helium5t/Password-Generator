import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tester {
    public Tester(){

    }
    public void test(Stage tochange){
        Text lol=new Text("LOLOLO");
        lol.setTranslateY(100);
        lol.setTranslateX(250);
        tochange.setScene(new Scene(new Group(lol),500,500));
    }
}
