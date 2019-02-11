import java.io.*;
import java.util.HashMap;

public class StoreManager implements Serializable {
    private HashMap<String,String> storage;
    private String storagepath;
    public StoreManager(String path){
        storagepath=path;
        try {
            InputStream in= StoreManager.class.getClassLoader().getResourceAsStream(path);
            ObjectInput oin=new ObjectInputStream(in);
            storage=(HashMap<String,String>) oin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void save(){
        try{
            OutputStream out= new FileOutputStream(StoreManager.class.getClassLoader().getResource(storagepath).getPath());
            ObjectOutput fout= new ObjectOutputStream(out);
            fout.writeObject(storage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
