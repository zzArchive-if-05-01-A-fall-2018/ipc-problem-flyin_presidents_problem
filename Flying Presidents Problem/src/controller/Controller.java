package controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javafx.util.Duration;
import model.JetFighter;
import model.President;
import model.Main;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    public TextArea textArea1;

    @FXML
    public TextArea textArea2;

    @FXML
    public TextArea log;

    @FXML
    private AnchorPane root;

    //       Short Introduction
    //--------------------------------
    // Philosopher == President
    // Fork == Jet


    String[] names = {"Trump", "Steinmeier", "Xi Jinping", "Macron", "Putin"};
    String[] jets = {"A", "B", "C", "D", "E"};

    JetFighter[] jetFighters = new JetFighter[5];
    Thread[] presidentsThreads = new Thread[5];

    public void initialize(URL url, ResourceBundle rb) {
        //Start FXML
        if(!Main.isSplashLoaded){
            loadSplashScreen();
        }

        //Dining Philosopher Start
        for (int i = 0; i < jetFighters.length; i++) {

            jetFighters[i] = new JetFighter(jets[i]);
        }

        for (int i = 0; i < presidentsThreads.length; i++) {

            if (i != presidentsThreads.length - 1) {

                President president = new President(jetFighters[i], jetFighters[i+1], names[i], this);
                presidentsThreads[i] = new Thread(president);

            } else {

                President president = new President(jetFighters[0], jetFighters[i], names[i], this);
                presidentsThreads[i] = new Thread(president);
            }
        }
    }

    @FXML
    private void handleStartButtonOnAction(ActionEvent event) {
        //Starts Threads
        for (Thread t: presidentsThreads) { t.start(); }
    }


    ///
    /// loadSplashScreen() is creating the SplashScreen
    /// and the fade in and fade out animation.
    ///
    private void loadSplashScreen(){
        try {
            Main.isSplashLoaded = true;
            StackPane pane = FXMLLoader.load(getClass().getResource("../view/SplashFXML.fxml"));
            root.getChildren().setAll(pane);

            //FADE IN TRANSITION -> SPLASH SCREEN
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            //STAY 3 SECONDS -> SPLASH SCREEN
            FadeTransition fade = new FadeTransition(Duration.seconds(2), pane);
            fade.setFromValue(1);
            fade.setToValue(1);
            fade.setCycleCount(1);

            //FADE OUT TRANSITION -> SPLASH SCREEN
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e)->{
                fade.play();
            });
            fade.setOnFinished((e)->{
                fadeOut.play();
            });

            fadeOut.setOnFinished((e)->{
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource("../view/SimulatorFXML.fxml"));
                    root.getChildren().setAll(parentContent);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        }catch (IOException ex){}
    }
}
