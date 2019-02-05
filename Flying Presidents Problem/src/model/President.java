package model;

import controller.Controller;

import java.util.Observable;

public class President extends Observable implements Runnable {

    private JetFighter jetFighter_left;
    private JetFighter jetFighter_right;
    private String aircraftName;
    private Controller controller;

    public President(JetFighter jetFighter_left, JetFighter jetFighter_right, String name, Controller controller) {
        this.jetFighter_left = jetFighter_left;
        this.jetFighter_right = jetFighter_right;
        this.aircraftName = name;
        this.controller = controller;
    }

    public void fly() {

        if (jetFighter_left.protect_the_president_plane()) {

            if (jetFighter_right.protect_the_president_plane()) {

                this.controller.log.setStyle("-fx-text-fill: red ;");
                this.controller.log.appendText("President " + aircraftName + " is flying \n");

                try {

                    Thread.sleep(2000);
                } catch (InterruptedException ex){
                    ex.printStackTrace();
                }

                this.controller.textArea1.clear();

                if(aircraftName.equals("Trump")) {

                    this.controller.textArea1.appendText("Steinmeier \n Xi Jinping \n Macron \n Putin \n");

                }else if(aircraftName.equals("Steinmeier")) {

                    this.controller.textArea1.appendText("Trump \n Xi Jinping \n Macron \n Putin \n");

                }else if(aircraftName.equals("Xi Jinping")) {

                    this.controller.textArea1.appendText("Trump \n Steinmeier \n Macron \n Putin \n");

                }else if(aircraftName.equals("Macron")) {

                    this.controller.textArea1.appendText("Trump \n Steinmeier \n Xi Jinping \n Putin \n");

                }else { // Putin
                    this.controller.textArea1.appendText("Trump \n Steinmeier \n Xi Jinping \n Macron\n");
                }


                this.controller.log.appendText("President " + aircraftName + " is waiting! \n");



                this.controller.textArea2.clear();

                if(jetFighter_left.getJetid() == "A" && jetFighter_right.getJetid() == "B"){
                    this.controller.textArea2.appendText("E\n");

                }else if(jetFighter_left.getJetid() == "C" && jetFighter_right.getJetid() == "D"){

                    this.controller.textArea2.appendText("B\n");

                }else {

                    this.controller.textArea2.appendText("A\n");

                }

                jetFighter_right.leave_the_president_plane();
                jetFighter_left.leave_the_president_plane();

            } else {

                jetFighter_left.leave_the_president_plane();
            }
        }
    }

    @Override
    public void run() {

        try {

            Thread.sleep(1000);

        } catch (InterruptedException ex) {

            ex.printStackTrace();
        }

        while (true) {

            fly();

        }
    }
}

