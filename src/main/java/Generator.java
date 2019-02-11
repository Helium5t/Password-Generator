import java.util.ArrayList;
import java.util.Random;

public class Generator {
    private int wordlength;
    private int digits;
    private Password key;

    public Generator(int wlen, int digs, String pwsite){
        wordlength=wlen;
        digits=digs;
        key=new Password(pwsite);
    }

    public Generator(int wlen,int digs){
        wordlength=wlen;
        digits=digs;
        key=new Password("");
    }

    public Password generate(){
        ArrayList<String> filtered=Filter.LenFilter(wordlength);
        if(filtered.size()!=0){
            Random rng=new Random();
            int place=rng.nextInt(filtered.size());
            String generated=filtered.get(place);
            for(int times=0; times<digits;times++){
                generated=generated + rng.nextInt(10);
            }
            System.err.println("STRING IS " + generated + " and key has " + key.getNote());
            key.encrypt(generated);
            Password returned=new Password(key.getNote());
            returned.encrypt(key.getPw());
            return returned;
        }
        else {
            return new Password("");
        }
    }
}
