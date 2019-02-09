import java.util.ArrayList;
import java.util.List;

public class OptionList {
    private ArrayList<Option> list;
    public OptionList(List<String> optlist){
        ArrayList<Option> options=new ArrayList<Option>(5);
        for (int i=0;i<optlist.size();i++){
            options.add(new Option(i,optlist.get(i)));
        }
        list=options;
    }
    public List<String> getContents(){
        ArrayList<String> contents= new ArrayList<String>(5);
        for (Option o: list) {
            contents.add(o.getContent());
        }
        return contents;
    }
    public int size(){
        return list.size();
    }
}
