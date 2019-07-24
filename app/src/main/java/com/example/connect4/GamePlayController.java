package com.example.connect4;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GamePlayController {

    //private GameView gameView;
    private LocationGridArray locationGridArray;
    private Rule rule;

    public GamePlayController(LocationGridArray locationGridArray, Rule mRule)
    {
        //this.gameView=mView;
        this.locationGridArray=locationGridArray;
        this.rule=mRule;


    }

    public  int checkWin() {
        final int HEIGHT = 6;
        final int WIDTH = 7;
        final int EMPTY_SLOT = 0;
        for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
            for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right
                int player = locationGridArray.circles.get(locationGridArray.loc[r][c]).getPlayer();
                if (player == EMPTY_SLOT)
                    continue; // don't check empty slots

                if (c + 3 < WIDTH &&
                        player == locationGridArray.circles.get(locationGridArray.loc[r][c+1]).getPlayer() && // look right
                        player == locationGridArray.circles.get(locationGridArray.loc[r][c+2]).getPlayer() &&
                        player == locationGridArray.circles.get(locationGridArray.loc[r][c+3]).getPlayer())
                {
                    locationGridArray.circles.get(locationGridArray.loc[r][c]).setWinning_circle(true);
                    locationGridArray.circles.get(locationGridArray.loc[r][c+1]).setWinning_circle(true);
                    locationGridArray.circles.get(locationGridArray.loc[r][c+2]).setWinning_circle(true);
                    locationGridArray.circles.get(locationGridArray.loc[r][c+3]).setWinning_circle(true);
                    return player;

                }
                if (r + 3 < HEIGHT) {
                    if (player == locationGridArray.circles.get(locationGridArray.loc[r+1][c]).getPlayer() && // look up
                            player == locationGridArray.circles.get(locationGridArray.loc[r+2][c]).getPlayer() &&
                            player == locationGridArray.circles.get(locationGridArray.loc[r+3][c]).getPlayer())
                    {
                        locationGridArray.circles.get(locationGridArray.loc[r][c]).setWinning_circle(true);
                        locationGridArray.circles.get(locationGridArray.loc[r+1][c]).setWinning_circle(true);
                        locationGridArray.circles.get(locationGridArray.loc[r+2][c]).setWinning_circle(true);
                        locationGridArray.circles.get(locationGridArray.loc[r+3][c]).setWinning_circle(true);
                        return player;
                    }
                    if (c + 3 < WIDTH &&
                            player == locationGridArray.circles.get(locationGridArray.loc[r+1][c+1]).getPlayer() && // look up & right
                            player == locationGridArray.circles.get(locationGridArray.loc[r+2][c+2]).getPlayer() &&
                            player == locationGridArray.circles.get(locationGridArray.loc[r+3][c+3]).getPlayer())
                    {
                        locationGridArray.circles.get(locationGridArray.loc[r][c]).setWinning_circle(true);
                        locationGridArray.circles.get(locationGridArray.loc[r+1][c+1]).setWinning_circle(true);
                        locationGridArray.circles.get(locationGridArray.loc[r+2][c+2]).setWinning_circle(true);
                        locationGridArray.circles.get(locationGridArray.loc[r+3][c+3]).setWinning_circle(true);
                        return player;
                    }
                    if (c - 3 >= 0 &&
                            player == locationGridArray.circles.get(locationGridArray.loc[r+1][c-1]).getPlayer() && // look up & left
                            player == locationGridArray.circles.get(locationGridArray.loc[r+2][c-2]).getPlayer() &&
                            player == locationGridArray.circles.get(locationGridArray.loc[r+3][c-3]).getPlayer())
                    {
                        locationGridArray.circles.get(locationGridArray.loc[r][c]).setWinning_circle(true);
                        locationGridArray.circles.get(locationGridArray.loc[r+1][c-1]).setWinning_circle(true);
                        locationGridArray.circles.get(locationGridArray.loc[r+2][c-2]).setWinning_circle(true);
                        locationGridArray.circles.get(locationGridArray.loc[r+3][c-3]).setWinning_circle(true);
                        return player;
                    }
                }
            }
        }
        return EMPTY_SLOT; // no winner found
    }


    public void undo_move(GameView gameView)
    {
        int last_move=rule.get_moves();
        boolean done=false;
        if (!rule.getWin()) {
            for (int i = 0; !done && last_move != -1; i++) {
                Location obj = locationGridArray.circles.get(locationGridArray.loc[i][last_move]);
                if (obj.getPlayer() == Player.PLAYER_1) {
                    rule.setTurn(Player.PLAYER_1);
                    obj.setPlayer(0);
                    done = true;
                } else if (obj.getPlayer() == Player.PLAYER_2) {
                    rule.setTurn(Player.PLAYER_2);
                    obj.setPlayer(0);
                    done = true;
                }

            }
        }
        gameView.invalidate();

    }



    public void dropBalls(int column, int turn)
    {
        if (checkWin()==0)
        {
            Log.d("msg", "No one wins");
        }else if (checkWin()==1)
        {
            Log.d("msg", "Player 1 Wins");
        }else if (checkWin()==2)
        {
            Log.d("msg", "Player 2 Wins");
        }
        boolean placed=false;
        for(int i=5; !placed;i--)
        {
            try
            {
                Location obj=locationGridArray.circles.get(locationGridArray.loc[i][column]);
                if (obj.getPlayer()==0)
                {
                    if (turn==Player.PLAYER_1){
                        obj.setPlayer(Player.PLAYER_1);
                        rule.push_moves(column);
                        rule.setTurn(Player.PLAYER_2);
                    }


                    else if (turn==Player.PLAYER_2){
                        obj.setPlayer(Player.PLAYER_2);
                        rule.push_moves(column);
                        rule.setTurn(Player.PLAYER_1);
                    }

                    placed=true;



                }
            }catch (ArrayIndexOutOfBoundsException e)
            {
                break;
            }





        }

    }









    /*float x,y;
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        x=event.getX();
        y=event.getY();
        Log.d("msg","Coordinates - ("+x+", "+y+")");
        Log.d("msg","width/x - "+gameView.getWidth()/x);
        gameView.performClick();
        return false;
    }*/


}
