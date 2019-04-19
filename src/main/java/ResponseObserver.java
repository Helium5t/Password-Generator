public class ResponseObserver {
    private String response;
    private boolean token;

    public ResponseObserver() {
        token = false;
        response = "";
    }

    public void addToken() {
        synchronized (this){
            token = true;
            this.notify();
        }
    }

    public void spendToken() {
        token = false;
    }

    public boolean getToken() {
        return token;
    }

    public String getResponse() {
        spendToken();
        return response;
    }

    public void setResponse(String set) {
        response = set;
    }
}
