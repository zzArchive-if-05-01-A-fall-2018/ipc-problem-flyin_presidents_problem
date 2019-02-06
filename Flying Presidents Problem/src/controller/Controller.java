package controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
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
    public ImageView trumpPic;
    public ImageView putinPic;
    public ImageView steinmeierPic;
    public ImageView xijinpingPic;
    public ImageView vanderbellenPic;

    @FXML
    public TextArea presWaiting;
    public TextArea jetWaiting;
    public TextArea flyin;

    @FXML
    private AnchorPane root;

    private Timeline timer = null;

    //       Short Introduction
    //--------------------------------
    // Philosopher == President
    // Fork == JetFighter

    President[] presidents = new President[5];
    JetFighter[] jetFighters = new JetFighter[5];


    public void initialize(URL url, ResourceBundle rb) {
        //Start FXML
        if(!Main.isSplashLoaded){
            loadSplashScreen();
        }
        jetFighters[0] = new JetFighter("A");
        jetFighters[1] = new JetFighter("B");
        jetFighters[2] = new JetFighter("C");
        jetFighters[3] = new JetFighter("D");
        jetFighters[4] = new JetFighter("E");

        presidents[0] = new President(jetFighters[0],jetFighters[1],"Trump");
        presidents[1] = new President(jetFighters[1],jetFighters[2],"Putin");
        presidents[2] = new President(jetFighters[2],jetFighters[3], "Steinmeier");
        presidents[3] = new President(jetFighters[3],jetFighters[4], "Xi Jinping");
        presidents[4] = new President(jetFighters[4],jetFighters[0],"Van der Bellen");


    }

    @FXML
    private void handleStartButtonOnAction(ActionEvent event) {
        //Starts Timeline
        timer = new Timeline(new KeyFrame(
                Duration.millis(3000),
                ae -> onTimerTick()));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.playFromStart();
    }

    private void onTimerTick() {
        for (int i = 0; i < presidents.length; i++) {
            presidents[i].tryToFly();
        }

        presWaiting.clear();
        jetWaiting.clear();
        clearImageViews();
        flyin.clear();

        for (int i = 0; i < presidents.length; i++) {
            if (presidents[i].checkFlight()){
                flyin.appendText(presidents[i].getName() + "     " + presidents[i].jetsToString() + "\n");
                checkPresAndShow(i);
            }
            else {
                presWaiting.appendText(presidents[i].getName() + "\n");
            }
        }

        for (int i = 0; i < jetFighters.length; i++) {
            if (jetFighters[i].isWaiting()){
                jetWaiting.appendText(jetFighters[i].getName() + "\n");
            }
        }

        allJetsNotOccupied();
    }

    private void checkPresAndShow(int i) {
        if (presidents[i].getName() == "Trump"){ trumpPic.setVisible(true);}
        else if (presidents[i].getName() == "Putin"){ putinPic.setVisible(true);}
        else if (presidents[i].getName() == "Steinmeier"){ steinmeierPic.setVisible(true);}
        else if (presidents[i].getName() == "Xi Jinping"){ xijinpingPic.setVisible(true);}
        else if (presidents[i].getName() == "Van der Bellen"){ vanderbellenPic.setVisible(true);}
    }

    private void allJetsNotOccupied() {
        for (int i = 0; i < jetFighters.length; i++) {
            jetFighters[i].setOccupiedBy(null);
        }
    }

    ///Clears all ImageViews
    private void clearImageViews(){
        trumpPic.setVisible(false);
        putinPic.setVisible(false);
        steinmeierPic.setVisible(false);
        xijinpingPic.setVisible(false);
        vanderbellenPic.setVisible(false);
    }

    /// loadSplashScreen() is creating the SplashScreen
    /// and the fade in and fade out animation.
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
