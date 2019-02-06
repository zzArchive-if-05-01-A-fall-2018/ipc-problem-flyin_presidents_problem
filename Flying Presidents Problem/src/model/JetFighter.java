package model;

public class JetFighter {

    private String name;
    private boolean waiting = true;
    private President occupiedBy;

    public JetFighter(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public President getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(President occupiedBy) {
        this.occupiedBy = occupiedBy;
        waiting = false;
        if (occupiedBy == null) waiting = true;
    }
}
