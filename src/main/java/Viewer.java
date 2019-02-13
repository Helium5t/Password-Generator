import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Viewer {
    private GeneralView window;
    private HashMap<String,String> storage;
    private String storagepath;
    public Viewer(String path,GeneralView screen){
        storagepath=path;
        window=screen;
        try{
            InputStream in=Viewer.class.getClassLoader().getResourceAsStream(path);
            ObjectInput oin=new ObjectInputStream(in);
            storage= ((HashMap<String,String>) oin.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            window.show("No previously stored passwords at the specified path");
            storage=new HashMap<String, String>();
        }
    }
    public String get(String site){
        String returned=storage.get(site);
        if (returned == null) {
            return "No password found related to choosen site";
        }
        else return returned;
    }
}
