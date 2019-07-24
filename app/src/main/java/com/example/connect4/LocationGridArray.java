package com.example.connect4;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class LocationGridArray {

    public List<Location> circles = new ArrayList<>();
    public int loc[][] = new int[6][7];
    private float width;
    private float height;
    private float radiusX;

    //MainActivity.MyView myView=new MainActivity.MyView();


    private float initial_x;
    //Log.d("msg","Reached checkpoint");
    //Log.d("msg", "init_x"+String.valueOf(initial_x));
    private float end_x;
    private float initial_y;
    private float end_y;
    Rule rule;

    public LocationGridArray()
    {

    }

    public LocationGridArray(float width, float height, Rule mRule)
    {

        this.width=width;
        Log.d("msg","width from constructor - "+width);
        Log.d("msg","this.width from constructor"+this.width);
        this.height=height;
        this.radiusX=(this.width/7.00f)/2.00f;
        this.initial_x=this.radiusX;
        Log.d("msg","initial_x from constructor - "+initial_x);
        this.end_x=this.initial_x+12*this.radiusX;
        this.initial_y=this.height-11*this.radiusX;
        this.end_y=this.initial_y+11*this.radiusX;
        this.rule=mRule;
    }

    public void setUpGrid() {
        int k = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                loc[i][j] = k;
                k++;
                Log.d("msg","Loc[][] - "+loc[i][j]);
            }
        }

        Log.d("msg","Reached the for loop in LocatinGridArray");
        Log.d("msg","initial_y - "+this.initial_y);
        Log.d("msg","end_y - "+end_y);
        Log.d("msg","initial_x - "+initial_x);
        Log.d("msg","end_x - "+end_x);
        Log.d("msg","radiusX - "+radiusX);


        for (float j = initial_y; j < end_y; j += 2 * radiusX) {
            Log.d("msg","Reached 1st for loop");
            for (float i = initial_x; i <= end_x; i += 2 * radiusX) {
                Location l = new Location();
                l.setLoc_x(i);
                l.setLoc_y(j);
                circles.add(l);
            }

        }

        //circles.get(loc[1][3]).setPlayer(2);
        //circles.get(loc[2][5]).setPlayer(1);
        Log.d("msg", "Circles - " + circles.get(loc[0][0]).getLoc_x());
        Log.d("msg", "Circles - " + circles.get(loc[0][1]).getLoc_x());
        Log.d("msg", "Circles - " + circles.get(loc[0][2]).getLoc_x());
        Log.d("msg", "Circles - " + circles.get(loc[0][3]).getLoc_x());
        /*Location l=new Location();
        l.setLoc_x(57);
        l.setLoc_y(57);
        circles.add(l);
        Location l1=new Location();
        l1.setLoc_x(100);
        l1.setLoc_y(100);
        circles.add(l1);*/
    }

    public void resetGrid()
    {
        for(int j=0;j<6;j++)
        {
            for(int i=0;i<7;i++)
            {
                circles.get(loc[j][i]).setPlayer(0);
                circles.get(loc[j][i]).setWinning_circle(false);

            }
        }

        rule.setTurn(rule.getChecked_first_turn_RadioButtonId()==R.id.first_turn_you? 1:2);
        rule.setWinner(0);
        rule.setWin(false);







    }
    //LocationGridArray locationGridArray=new LocationGridArray();

}

