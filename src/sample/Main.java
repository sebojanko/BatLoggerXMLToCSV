package sample;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by sebo on 12/02/18.
 * janko.sebastian@gmail.com
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        UICreator ui = new UICreator(primaryStage);

    }
}
