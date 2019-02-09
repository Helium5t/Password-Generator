public class Option {
    private int id;
    private String content;
    public Option(int index, String message){
        id=index;
        content=message;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
