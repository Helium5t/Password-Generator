import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * 1= main menu
 * 2= gen menu
 * 3= list menu
 * 4= save menu
 * 5=gen menu 2
 * 6=gen menu 3
 * 7=message scene
 * 8=save or close
 * 9=close
 *
 * 0= end
 */
public class GraphicsBuilder {
    public static Scene getScene(int id,final ChoiceState choiceState,final ResponseObserver reply){
        switch (id){
            case 0: //End
                Text thanks=new Text("Thank you for using password generator");
                thanks.setFont(Font.font("Helvetica", FontWeight.BOLD,40));
                thanks.setWrappingWidth(500);
                thanks.setTextAlignment(TextAlignment.CENTER);
                thanks.setY(250);
                Button close=new Button("Close");
                close.setTranslateX(220);
                close.setTranslateY(thanks.getY()+150);
                Group end=new Group(thanks,close);
                EventHandler<MouseEvent> x=new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        GraphicView.close();
                    }
                };
                close.addEventHandler(MouseEvent.MOUSE_CLICKED,x);
                return new Scene(end,500,500, Paint.valueOf("red"));
            case 1: //Main Menu
                Text choose=new Text("What do you want to do?");
                choose.setFont(Font.font("Helvetica",30));
                choose.setX(100);
                choose.setY(200);
                Button generate=new Button("Generate new password");
                generate.setTranslateY(250);
                generate.setTranslateX(100);
                Button list=new Button("See password list");
                list.setTranslateY(250);
                list.setTranslateX(generate.getTranslateX()+200);
                Group main=new Group(choose,generate,list);
                EventHandler<MouseEvent> gen=new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        synchronized (choiceState) {
                            choiceState.setChoice(0);
                            choiceState.addToken();
                            choiceState.notify();
                        }
                        GraphicView.update();
                        GraphicView.changestage();
                    }
                };
                EventHandler<MouseEvent> lstd=new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        synchronized (choiceState) {
                            choiceState.setChoice(1);
                            choiceState.addToken();
                            choiceState.notify();
                        }
                        GraphicView.update();
                        GraphicView.changestage();
                    }
                };
                generate.addEventHandler(MouseEvent.MOUSE_CLICKED,gen);
                list.addEventHandler(MouseEvent.MOUSE_CLICKED,lstd);
                return new Scene(main,500,500, Paint.valueOf("green"));
            case 2:
                Text wlen=new Text("Input word length");
                wlen.setFont(Font.font("Helvetica",30));
                wlen.setX(100);
                wlen.setY(200);
                final TextField len=new TextField("Input length in numbers");
                len.setTranslateX(150);
                len.setTranslateY(300);
                Button ok=new Button("OK");
                ok.setTranslateX(300);
                ok.setTranslateY(300);
                EventHandler<MouseEvent> input= new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        String inserted=len.getText();
                        reply.setResponse(inserted);
                        reply.addToken();
                        GraphicView.changestage(5);
                    }
                };
                ok.addEventHandler(MouseEvent.MOUSE_CLICKED,input);
                Group gen1=new Group(wlen,len,ok);
                return new Scene(gen1,500,500,Paint.valueOf("green"));
            case 3:
                Text where=new Text("Where are the password stored?");
                where.setFont(Font.font("Helvetica",30));
                where.setWrappingWidth(500);
                where.setTextAlignment(TextAlignment.CENTER);
                where.setY(100);
                Button def=new Button("Default path");
                def.setTranslateX(150);
                def.setTranslateY(250);
                Button custom=new Button("Custom path");
                custom.setTranslateY(250);
                custom.setTranslateX(350);
                EventHandler<MouseEvent> dflt=new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        synchronized (choiceState){
                            choiceState.setChoice(0);
                            choiceState.addToken();
                            choiceState.notify();
                        }
                        GraphicView.changestage(11);
                    }
                };
                EventHandler<MouseEvent> cstm=new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        synchronized (choiceState){
                            choiceState.setChoice(1);
                            choiceState.addToken();
                            choiceState.notify();
                        }
                        GraphicView.changestage(11);
                    }
                };
                def.addEventHandler(MouseEvent.MOUSE_CLICKED,dflt);
                custom.addEventHandler(MouseEvent.MOUSE_CLICKED,cstm);
                Group savemen=new Group(where,def,custom);
                return new Scene(savemen,500,500,Paint.valueOf("Green"));
            case 4:
                where=new Text("Where do you want to store the password?");
                where.setFont(Font.font("Helvetica",30));
                where.setWrappingWidth(500);
                where.setTextAlignment(TextAlignment.CENTER);
                where.setY(100);
                def=new Button("Default path");
                def.setTranslateX(150);
                def.setTranslateY(250);
                custom=new Button("Custom path");
                custom.setTranslateY(250);
                custom.setTranslateX(350);
                dflt=new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        synchronized (choiceState){
                            choiceState.setChoice(0);
                            choiceState.addToken();
                            choiceState.notify();
                        }
                        GraphicView.update();
                        GraphicView.changestage();
                    }
                };
                cstm=new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        synchronized (choiceState){
                            choiceState.setChoice(1);
                            choiceState.addToken();
                            choiceState.notify();
                        }
                        GraphicView.changestage(10);
                    }
                };
                def.addEventHandler(MouseEvent.MOUSE_CLICKED,dflt);
                custom.addEventHandler(MouseEvent.MOUSE_CLICKED,cstm);
                savemen=new Group(where,def,custom);
                return new Scene(savemen,500,500,Paint.valueOf("Green"));
        //TODO: save menu
            case 5:
                Text digs=new Text("Input number of digits");
                digs.setFont(Font.font("Helvetica",30));
                digs.setX(100);
                digs.setY(200);
                final TextField dgs=new TextField("Input number of digs in numbers");
                dgs.setTranslateX(150);
                dgs.setTranslateY(300);
                ok=new Button("OK");
                ok.setTranslateX(300);
                ok.setTranslateY(300);
                input=new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        String inserted=dgs.getText();
                        reply.setResponse(inserted);
                        reply.addToken();
                        GraphicView.changestage(6);
                    }
                };
                ok.addEventHandler(MouseEvent.MOUSE_CLICKED,input);
                Group gen2=new Group(digs,dgs,ok);
                return new Scene(gen2,500,500,Paint.valueOf("green"));
            case 6:
                Text site=new Text("Input site related to password");
                site.setFont(Font.font("Helvetica",30));
                site.setX(100);
                site.setY(200);
                final TextField ste=new TextField("Site");
                ste.setTranslateX(150);
                ste.setTranslateY(300);
                ok=new Button("OK");
                ok.setTranslateX(300);
                ok.setTranslateY(300);
                input=new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        String inserted=ste.getText();
                        reply.setResponse(inserted);
                        reply.addToken();
                        GraphicView.changestage(7);
                    }
                };
                ok.addEventHandler(MouseEvent.MOUSE_CLICKED,input);
                Group gen3=new Group(site,ste,ok);
                return new Scene(gen3,500,500,Paint.valueOf("green"));
            case 7:
                String pw="";
                synchronized (reply){
                    try{
                        reply.wait(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (!reply.getToken()){
                        try {
                            reply.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    pw=reply.getResponse();
                }
                Text psw=new Text(pw);
                psw.setX(100);
                psw.setY(150);
                psw.setFont(Font.font("Helvetica",20));
                ok=new Button("OK");
                ok.setTranslateX(200);
                ok.setTranslateY(200);
                input=new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        GraphicView.changestage(8);
                    }
                };
                ok.addEventHandler(MouseEvent.MOUSE_CLICKED,input);
                Group gen4=new Group(psw,ok);
                return new Scene(gen4, 500,500,Paint.valueOf("green"));
            case 8:
                choose=new Text("What do you want to do next?");
                choose.setFont(Font.font("Helvetica",30));
                choose.setWrappingWidth(500);
                choose.setTextAlignment(TextAlignment.CENTER);
                choose.setY(180);
                Button save=new Button("Save");
                Button cls=new Button("Close");
                save.setTranslateX(170);
                save.setTranslateY(250);
                cls.setTranslateX(280);
                cls.setTranslateY(250);
                EventHandler savevnt=new EventHandler() {
                    public void handle(Event event) {
                        synchronized (choiceState) {
                            choiceState.setChoice(0);
                            choiceState.addToken();
                            choiceState.notify();
                        }
                        GraphicView.update();
                        GraphicView.changestage();
                    }
                };
                EventHandler closevnt=new EventHandler() {
                    public void handle(Event event){
                        synchronized (choiceState) {
                            choiceState.setChoice(1);
                            choiceState.addToken();
                            choiceState.notify();
                        }
                        GraphicView.changestage(9);
                    }
                };
                save.addEventHandler(MouseEvent.MOUSE_CLICKED,savevnt);
                cls.addEventHandler(MouseEvent.MOUSE_CLICKED,closevnt);
                Group gen5=new Group(choose,save,cls);
                return new Scene(gen5,500,500,Paint.valueOf("green"));
            case 9:
                Text msg=new Text("Password saved to clipoard");
                msg.setFont(Font.font("Helvetica",30));
                msg.setY(200);
                msg.setWrappingWidth(500);
                msg.setTextAlignment(TextAlignment.CENTER);
                ok=new Button("Close App");
                ok.setTranslateY(msg.getY()+50);
                ok.setTranslateX(220);
                closevnt=new EventHandler() {
                    public void handle(Event event) {
                        GraphicView.update();
                        GraphicView.changestage();
                    }
                };
                ok.addEventHandler(MouseEvent.MOUSE_CLICKED,closevnt);
                Group gen6=new Group(msg,ok);
                return new Scene(gen6,500,500,Paint.valueOf("green"));
            case 10:
                Text pathget=new Text("Insert custom path below");
                pathget.setFont(Font.font("Helvetica",30));
                pathget.setWrappingWidth(500);
                pathget.setTextAlignment(TextAlignment.CENTER);
                pathget.setY(100);
                final TextField cpath=new TextField("Insert here");
                cpath.setTranslateX(150);
                cpath.setTranslateY(250);
                Button insert=new Button("Insert");
                insert.setTranslateX(150);
                insert.setTranslateY(cpath.getTranslateY()+50);
                EventHandler<MouseEvent> confirm=new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        String inserted=cpath.getText();
                        synchronized (reply) {
                            reply.setResponse(inserted);
                            reply.addToken();
                            reply.notify();
                        }
                        GraphicView.update();
                        GraphicView.changestage();
                    }
                };
                insert.addEventHandler(MouseEvent.MOUSE_CLICKED,confirm);
                Group custompath=new Group(pathget,cpath,insert);
                return new Scene(custompath,500,500,Paint.valueOf("green"));
            case 11:
                String msgshown="";
                synchronized (reply){
                    try {
                        reply.wait(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (!reply.getToken()){
                        try{
                            reply.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    msgshown=reply.getResponse();
                    reply.spendToken();
                }
                Text shown=new Text(msgshown);
                shown.setFont(Font.font("Helvetica",30));
                shown.setWrappingWidth(500);
                shown.setTextAlignment(TextAlignment.CENTER);
                shown.setY(100);
                if(msgshown.contains("vault")) {
                    Text enter = new Text("Enter site needed");
                    enter.setWrappingWidth(500);
                    enter.setTextAlignment(TextAlignment.CENTER);
                    enter.setY(150);
                    final TextField sitefield = new TextField("Enter site here");
                    sitefield.setTranslateX(150);
                    sitefield.setTranslateY(enter.getY() + 50);
                    Button siteinsert = new Button("Insert");
                    siteinsert.setTranslateX(280);
                    siteinsert.setTranslateY(sitefield.getTranslateY());
                    EventHandler<MouseEvent> insertsite = new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent event) {
                            String inserted = "";
                            inserted = sitefield.getText();
                            synchronized (reply) {
                                reply.setResponse(inserted);
                                reply.addToken();
                                reply.notify();
                            }
                            GraphicView.changestage(12);
                        }
                    };
                    siteinsert.addEventHandler(MouseEvent.MOUSE_CLICKED,insertsite);
                    Group list1=new Group(shown,enter,sitefield,siteinsert);
                    return new Scene(list1,500,500,Paint.valueOf("green"));
                }
                else{
                    Button quit=new Button("OK");
                    quit.setTranslateY(shown.getY()+50);
                    quit.setTranslateX(230);
                    EventHandler<MouseEvent> back=new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent event) {
                            GraphicView.update();
                            GraphicView.changestage();
                        }
                    };
                    quit.addEventHandler(MouseEvent.MOUSE_CLICKED,back);
                    return new Scene(new Group(shown,quit),500,500,Paint.valueOf("Green"));
                }
            case 12:
                String fnd="";
                synchronized (reply){
                    try {
                        reply.wait(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (!reply.getToken()){
                        try {
                            reply.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        fnd=reply.getResponse();
                        reply.spendToken();
                        reply.notify();
                    }
                }
                Text found=new Text(fnd);
                found.setFont(Font.font("Helvetica",30));
                found.setWrappingWidth(500);
                found.setTextAlignment(TextAlignment.CENTER);
                found.setY(150);
                Button quit=new Button("OK");
                quit.setTranslateY(found.getY()+50);
                quit.setTranslateX(230);
                EventHandler<MouseEvent> back=new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        GraphicView.update();
                        GraphicView.changestage();
                    }
                };
                quit.addEventHandler(MouseEvent.MOUSE_CLICKED,back);
                return new Scene(new Group(found,quit),500,500,Paint.valueOf("green"));
            default:
                return null;

        }
    }
}
