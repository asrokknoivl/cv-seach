package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import fr.univ_lyon1.info.m1.cv_search.model.Model;
import fr.univ_lyon1.info.m1.cv_search.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {

    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model();
        Controller controller = new Controller(model);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        new View(controller, stage,(int) screenSize.getWidth() - 100, (int)screenSize.getHeight() - 100).show();
        //new View(controller, new Stage(), 500, 500).show();
    }


    /**
     * A main method in case the user launches the application using
     * App as the main class.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
