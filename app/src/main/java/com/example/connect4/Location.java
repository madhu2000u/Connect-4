package com.example.connect4;

public final class Location {

    private float loc_x;
    private float loc_y;
    private int player=0; //default is 0
    private boolean winning_circle=false;



        /*Location(float loc_x, float loc_y)
        {
            this.loc_x=loc_x;
            this.loc_y=loc_y;
        }*/

    public void setLoc_x(float loc_x) {this.loc_x = loc_x;}
    public float getLoc_x(){return loc_x;}


    public void setLoc_y(float loc_y) {this.loc_y = loc_y;}
    public float getLoc_y(){return loc_y;}

    public void setPlayer(int player) {this.player = player;}
    public int getPlayer(){return player;}

    public void setWinning_circle(boolean winning_circle){this.winning_circle=winning_circle;}
    public boolean getWinning_circle() {return this.winning_circle;}


}
