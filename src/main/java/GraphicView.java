import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GraphicView extends Application {
    private static AppObserver appObserver;
    private static Stage stage;
    private static String currentState;
    private static int currentStateid;
    private static ChoiceState choice;
    private static ResponseObserver reply;

    public static void build(AppObserver observer,ChoiceState choiceState,ResponseObserver responseObserver){
        choice=choiceState;
        appObserver=observer;
        reply=responseObserver;
    }

    public void start(final Stage primaryStage) throws Exception {
        Text welcome=new Text("WELCOME TO PASSWORD GENERATOR");
        welcome.setFont(Font.font("Helvetica", FontWeight.BOLD,35));
        Text press=new Text("Press Start to begin");
        welcome.setFont(Font.font("Helvetica",20));
        Button start=new Button("Start");
        Group root=new Group(welcome,press,start);
        final Scene fullscene=new Scene(root,500,500, Paint.valueOf("red"));
        welcome.setX(70);
        welcome.setY(250);
        press.setY(welcome.getY()+40);
        press.setX(190);
        start.setTranslateY(press.getY()+50);
        start.setTranslateX(230);
        stage=primaryStage;
        EventHandler<MouseEvent> nextState=new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                update();
                primaryStage.setScene(GraphicsBuilder.getScene(currentStateid,choice,reply));
                primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent event) {
                        System.exit(1);
                    }
                });
            }
        };
        start.addEventHandler(MouseEvent.MOUSE_CLICKED,nextState);
        EventHandler<WindowEvent> close=new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                System.exit(1);
            }
        };
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,close);
        //TODO: events and position
        primaryStage.setScene(fullscene);
        primaryStage.show();
        //TODO: dynamic options
    }

    public static void changestage(){
        stage.setScene(GraphicsBuilder.getScene(currentStateid,choice, reply));
        EventHandler<WindowEvent> close=new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                System.exit(1);
            }
        };
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,close);

    }

    public static void changestage(int state){
        stage.setScene(GraphicsBuilder.getScene(state,choice,reply));
    }

    public static void close(){
        stage.close();
    }

    public static void update(){
        String nextstate=appObserver.getStateName();
        while(nextstate.equals(currentState)){
            synchronized (appObserver){
                try {
                    appObserver.wait(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nextstate=appObserver.getStateName();
            }
        }
        currentState=appObserver.getStateName();
        currentStateid=appObserver.getStateID();
    }

    public static void show(String message){
        Stage secstage=new Stage();
        Text mess=new Text(message);
        mess.setX(200);
        mess.setY(250);
        Group shown=new Group(mess);
        secstage.setScene(new Scene(shown,500,500,Paint.valueOf("green")));
    }

    public static int getCurrentStateid(){
        return currentStateid;
    }

    public static String getCurrentState(){
        return currentState;
    }

    public static void start(){
        launch();
    }
}
