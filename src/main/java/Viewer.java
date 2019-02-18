import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Viewer {
    private HashMap<String,String> storage;
    private String storagepath;
    public Viewer(String path,StoreManager file){
        storagepath=path;
        storage=file.getStorage();
    }
    public int size(){
        return storage.size();
    }
    public String get(String site){
        String returned=storage.get(site);
        if (returned == null) {
            return "No password found related to choosen site";
        }
        else return returned;
    }
}
