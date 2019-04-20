import java.io.*;
import java.util.HashMap;

public class StoreManager implements Serializable {
    protected HashMap<String,String> storage; // < site , password >
    private String storagepath;
    public StoreManager(String path){
        try {
            InputStream in= StoreManager.class.getClassLoader().getResourceAsStream(path);
            ObjectInput oin=new ObjectInputStream(in);
            storage=(HashMap<String,String>) oin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e2){
            HashMap<String,String> hash=new HashMap<String, String>();
            try {
                File origin=new File(StoreManager.class.getClassLoader().getResource("Dictionary.txt").getPath());
                File parent=origin.getParentFile().getParentFile().getParentFile();
                String respath = parent.getPath() + "\\src\\main\\resources";
                File directory=new File(respath);
                File vault=new File(directory,"Vault.pw");
                storagepath=vault.getPath();
                OutputStream out=new FileOutputStream(storagepath);
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
        }
    }
    public void save(){
        try{
            OutputStream out= new FileOutputStream(storagepath);
            ObjectOutput fout= new ObjectOutputStream(out);
            fout.writeObject(storage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){

        }
    }

    public HashMap<String, String> getStorage() {
        return storage;
    }

    public String getStoragepath() {
        return storagepath;
    }

    public void add(Password pw){

        if(pw.getNote().equals("")){
            storage.put("No Site",pw.getPw());
        }
        else {
            storage.put(pw.getNote(),pw.getPw());
        }
    }
}
