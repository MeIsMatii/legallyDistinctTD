import javafx.application.Application;

public class MainLauncher {
    public static void main(String[] args) {
        // Force JavaFX to use software rendering fallback
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.verbose", "true");

        Application.launch(greenfoot.export.GreenfootScenarioApplication.class, args);
    }
}