import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

@Deprecated
public class SceneBuilder {
    public static Parent getNode(int stateid){
        switch (stateid) {
            case 0:
                Group endscreen=new Group();
                ObservableList endtext=endscreen.getChildren();
                Text thanks=new Text("thanks for using pwgen");
                endtext.add(thanks);
                return endscreen;
            case 1:
                //TODO: main menu
                Group newScene = new Group();
                ObservableList elements = newScene.getChildren();
                Button generate = new Button("Generate new password");
                Button list = new Button("See password list");
                Button end = new Button("End");
                elements.add(generate);
                elements.add(list);
                elements.add(end);
                generate.setTranslateX(100);
                generate.setTranslateY(100);
                list.setTranslateX(generate.getWidth() + 50);
                list.setTranslateY(generate.getTranslateY());
                end.setTranslateX((generate.getTranslateX() + list.getTranslateX()) / 2);
                end.setTranslateY(generate.getTranslateY() + generate.getHeight() + 50);
                EventHandler<MouseEvent> choiceone = new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        GUI.setchoice(0);
                        System.out.println("now " + GUI.getChoice());
                    }
                };
                EventHandler<MouseEvent> choicetwo = new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        GUI.setchoice(1);
                        System.out.println("now " + GUI.getChoice());
                    }
                };
                EventHandler<MouseEvent> choicethree = new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        GUI.setchoice(2);
                        System.out.println("now " + GUI.getChoice());
                    }
                };
                generate.addEventHandler(MouseEvent.MOUSE_CLICKED,choiceone);
                list.addEventHandler(MouseEvent.MOUSE_CLICKED,choicetwo);
                end.addEventHandler(MouseEvent.MOUSE_CLICKED,choicethree);
                return newScene;
            case 2:
                //TODO: generation menu
                newScene=new Group();
                elements=newScene.getChildren();
                Text title=new Text("Insert the properties of the generated password and its related site");
                final TextField wlen=new TextField("Insert word length");
                final TextField digits=new TextField("Insert number of digits");
                final TextField site=new TextField("Insert related sites");
                end=new Button("Insert");
                title.setTranslateX(250);
                title.setTranslateY(50);
                wlen.setTranslateY(100);
                wlen.setTranslateX(100);
                digits.setTranslateX(100);
                digits.setTranslateY(150);
                site.setTranslateY(200);
                site.setTranslateX(100);
                end.setTranslateY(250);
                end.setTranslateX(100);
                EventHandler<MouseEvent> inserted=new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        GUI.setDigits(digits.getCharacters().toString());
                        GUI.setSite(site.getCharacters().toString());
                        GUI.setWlen(wlen.getCharacters().toString());
                    }
                };
                end.addEventHandler(MouseEvent.MOUSE_CLICKED,inserted);
                elements.add(title);
                elements.add(wlen);
                elements.add(digits);
                elements.add(site);
                elements.add(end);
                return newScene;
            case 3:
                //TODO: list menu
            case 4:
                //TODO: save menu
        }
        return null;
    }
}
