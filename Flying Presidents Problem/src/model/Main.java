package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static Boolean isSplashLoaded = false;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../view/SimulatorFXML.fxml"));
        primaryStage.setTitle("Flying Presidents Problem");
        primaryStage.setScene(new Scene(root));

        //Trump Picture as Icon
        primaryStage.getIcons().add(
                new Image(
                        Main.class.getResourceAsStream( "trump.png" )));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
