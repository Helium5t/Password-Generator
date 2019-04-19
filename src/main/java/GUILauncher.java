@Deprecated

public class GUILauncher implements Runnable {
    public void run() {
        System.out.println("BUILDING SCENE");
        GUI.build();
        System.out.println("BUILT SCENE");
    }
}
