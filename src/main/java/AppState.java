public abstract class AppState {
    /**
     * 1= main menu
     * 2= gen menu
     * 3= list menu
     * 4= save menu
     * 0= end
     * @return
     */
    public abstract AppState nextState();
    public abstract String currentState();
    public abstract int getid();
}
