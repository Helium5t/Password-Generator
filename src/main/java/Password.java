public class Password {
    private String pw;
    private String note;
    public Password(String site){
        note=site;
        pw="";
    }

    public void encrypt(String key){
        pw=key;
    }

    public String getNote() {
        return note;
    }

    public String getPw() {
        return pw;
    }
}
