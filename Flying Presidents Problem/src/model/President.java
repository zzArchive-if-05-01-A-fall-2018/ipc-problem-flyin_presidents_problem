package model;

public class President {

    private JetFighter[] jetfighters = new JetFighter[2];
    private String name;
    private int timesFlown = 0;

    public President(JetFighter jetFighter_left, JetFighter jetFighter_right, String name) {
        this.jetfighters[0] = jetFighter_left;
        this.jetfighters[1] = jetFighter_right;
        this.name = name;
    }


    public void tryToFly() {
        for (int i = 0; i < 2; i++) {
            if (jetfighters[i].isWaiting() || (jetfighters[i].getOccupiedBy().getTimesFlown() > this.timesFlown)){
                jetfighters[i].setOccupiedBy(this);
            }
            else return;
        }
    }

    private int getTimesFlown() {
        return timesFlown;
    }

    public boolean checkFlight() {
        if (jetfighters[0].getOccupiedBy() == this && jetfighters[1].getOccupiedBy() == this){
            timesFlown++;
            return true;
        }
        if (jetfighters[0].getOccupiedBy() == this) jetfighters[0].setOccupiedBy(null);
        if (jetfighters[1].getOccupiedBy() == this) jetfighters[1].setOccupiedBy(null);
        return false;
    }

    public String getName() {
        return name;
    }

    public String jetsToString() {
        return jetfighters[0].getName() + ", " + jetfighters[1].getName();
    }
}

