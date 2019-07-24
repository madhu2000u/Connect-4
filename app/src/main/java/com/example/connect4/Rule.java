package com.example.connect4;


import android.util.Log;
import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Stack;


public class Rule implements Serializable {

    private int checked_first_turn_RadioButtonId;
    private int checked_choose_color_RadioButtonId;
    private int turn;
    private int player_1_color, player_2_color;
    private boolean isWin=false;
    private Stack<Integer> moves=new Stack<>();
    private int winner=0;   // 0 means no winner

    public Rule(int checked_first_turn_RadioButtonId, int checked_choose_color_RadioButtonId)
    {
        this.checked_first_turn_RadioButtonId = checked_first_turn_RadioButtonId;
        this.checked_choose_color_RadioButtonId=checked_choose_color_RadioButtonId;
        Log.d("msg","checked_choose_radioButtonId - "+checked_choose_color_RadioButtonId);
        Log.d("msg","Red_radiobuttonId - "+R.id.red);
        if (this.checked_choose_color_RadioButtonId==R.id.red)
        {
            this.player_1_color = R.color.red_circle_color;
            this.player_2_color = R.color.yellow_circle_color;
        }
        else
        {

            this.player_1_color = R.color.yellow_circle_color;
            this.player_2_color=R.color.red_circle_color;
        }
        if(this.checked_first_turn_RadioButtonId==R.id.first_turn_you)
            this.turn=Player.PLAYER_1;
        else
            this.turn=Player.PLAYER_2;


    }


    public void setWin(boolean win) {
        this.isWin = win;
    }

    public boolean getWin(){
        return isWin;
    }

    public void push_moves(int move)
    {
        this.moves.push(move);
    }

    public int get_moves()
    {
        try {
            return this.moves.pop();
        }catch (EmptyStackException e){return -1;}


    }

    public int getChecked_first_turn_RadioButtonId() {
        return checked_first_turn_RadioButtonId;
    }

    public int getChecked_choose_color_RadioButtonId() {return checked_choose_color_RadioButtonId;}

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }

    public int getPlayer_1_color() {
        return player_1_color;
    }

    public int getPlayer_2_color() {
        return player_2_color;
    }

    public void setWinner(int winner) {this.winner = winner;}

    public int getWinner() {return winner;}
}
