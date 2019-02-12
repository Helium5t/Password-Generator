import java.io.*;
import java.util.HashMap;

public class StoreManager implements Serializable {
    private HashMap<String,String> storage; // < site , password >
    private String storagepath;
    public StoreManager(String path){
        storagepath=path;
        try {
            InputStream in= StoreManager.class.getClassLoader().getResourceAsStream(path);
            ObjectInput oin=new ObjectInputStream(in);
            storage=(HashMap<String,String>) oin.readObject();
        } catch (IOException e) {
            System.out.println("No previous versions of Vault, initializing...");
            HashMap<String,String> hash=new HashMap<String, String>();
            try {
                OutputStream out=new FileOutputStream(StoreManager.class.getClassLoader().getResource(storagepath).getPath());
                ObjectOutput fout= new ObjectOutputStream(out);
                fout.writeObject(hash);
                storage=hash;
                fout.close();
                out.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("CLASSNOTFOUND");
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

    public void add(Password pw){
        storage.put(pw.getNote(),pw.getPw());
    }
}
