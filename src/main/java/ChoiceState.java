public class ChoiceState {
    private boolean token;
    private int choice;

    public ChoiceState() {
        token = false;
        choice = -1;
    }

    public void addToken() {
        token = true;
    }

    public void spendToken() {
        token = false;
    }

    public boolean getToken() {
        return token;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public int getChoice() {
        spendToken();
        return choice;
    }
}
