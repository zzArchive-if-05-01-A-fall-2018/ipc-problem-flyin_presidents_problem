package model;

import java.util.concurrent.Semaphore;

public class JetFighter {

    public Semaphore jetFighter = new Semaphore(1);
    public String jet_id;

    public JetFighter(String jet_id){

        this.jet_id = jet_id;
    }

    public String getJetid() {

        return jet_id;
    }

    public boolean protect_the_president_plane(){

        return jetFighter.tryAcquire();
    }
    public void leave_the_president_plane(){

        jetFighter.release();
    }
}
