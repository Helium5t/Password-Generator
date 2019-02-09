import java.util.ArrayList;
import java.util.Random;

public class Generator {
    private int wordlength;
    private int digits;
    private String site;
    private String pw;

    public Generator(int wlen, int digs, String pwsite){
        wordlength=wlen;
        digits=digs;
        site=pwsite;
    }

    public Generator(int wlen,int digs){
        wordlength=wlen;
        digits=digs;
    }

    public String generate(){
        ArrayList<String> filtered=Filter.LenFilter(wordlength);
        if(filtered.size()!=0){
            Random rng=new Random();
            int place=rng.nextInt(filtered.size());
            String generated=filtered.get(place);
            for(int times=0; times<digits;times++){
                generated=generated + rng.nextInt(10);
            }
            return generated;
        }
        else {
            return "";
        }
    }
}
