public class GraphicsInterface implements GeneralView {
    private ChoiceState choiceobserver;
    private ResponseObserver responseObserver;

    public GraphicsInterface(AppObserver observer){
        choiceobserver=new ChoiceState();
        responseObserver=new ResponseObserver();
        GraphicView.build(observer,choiceobserver,responseObserver);
        new Thread(new GraphicsLauncher()).start();
        //TODO: new graphics launcher
        //TODO: new state observer
        //TODO: new thread.launch graphics
        //TODO: new thread.launch observer
    }

    public void show(String message) {
        synchronized (responseObserver){
            responseObserver.setResponse(message);
            responseObserver.addToken();
            responseObserver.notify();
        }
    }

    public int choose(OptionList choices) {
        int chosen=-1;
        synchronized (choiceobserver){
            while(!choiceobserver.getToken()){
                try {
                    choiceobserver.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            chosen=choiceobserver.getChoice();
            choiceobserver.spendToken();
            choiceobserver.notify();
        }
        if(chosen<=choices.size())
            return chosen;
        else
            return -1;
    }

    public String ask(String message) {
        String reply="";
        synchronized (responseObserver){
            while (!responseObserver.getToken()){
                try {
                    responseObserver.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reply=responseObserver.getResponse();
            responseObserver.spendToken();
            responseObserver.notify();
        }
        return reply;
    }

    public String getPath() {
        return null;
    }
}
