package com.example.connect4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;



public class GameView extends View {
    private LocationGridArray locationGridArray;
    private Rule rule;
    private MediaPlayer ta_da;
    private GamePlayController gamePlayController;


    public GameView(Context context, MediaPlayer ta_da, Rule rule, LocationGridArray locationGridArray, GamePlayController gamePlayController)
    {
        super(context);
        this.locationGridArray=locationGridArray;
        this.gamePlayController=gamePlayController;
        this.rule=rule;
        this.ta_da=ta_da;

    }



    float width=getWidth();
    float height=getHeight();
    float radiusX=(width/7.00f)/2.00f;


    float initial_x=radiusX;
    //Log.d("msg","Reached checkpoint");
    //Log.d("msg", "init_x"+String.valueOf(initial_x));
    float end_x=initial_x+12*radiusX;
    public float initial_y=height-11*radiusX;
    float end_y=initial_y+11*radiusX;


    Paint canvas_background_paint= new Paint();
    Paint white_circle_paint=new Paint();
    Paint player_1=new Paint();
    Paint player_2=new Paint();
    Paint test_paint=new Paint(); //TODO Remove this when submitting
    Paint winner_text=new Paint();
    Paint text_paint=new Paint();



    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        float width=getWidth();
        float height=getHeight();
        Log.d("msg","Height from onDraw - "+height);
        float radiusX=(width/7.00f)/2.00f;
        float initial_x=radiusX;            //Though it is redundant, it is just for my readability
        //Log.d("msg","Reached checkpoint");
        //Log.d("msg", "init_x"+String.valueOf(initial_x));
        double end_x=initial_x+12*radiusX;
        float initial_y=height-11*radiusX;
        float end_y=initial_y+11*radiusX;
        Log.d("msg","Inside onDraw()");




        canvas_background_paint.setStyle(Paint.Style.FILL);
        canvas_background_paint.setColor(getResources().getColor(R.color.canvas_background));
        canvas_background_paint.setAntiAlias(true);
        canvas.drawPaint(canvas_background_paint);




        white_circle_paint.setColor(Color.WHITE);
        white_circle_paint.setStyle(Paint.Style.FILL);
        white_circle_paint.setAntiAlias(true);

        text_paint.setColor(Color.WHITE);
        text_paint.setStyle(Paint.Style.FILL);
        text_paint.setAntiAlias(true);
        text_paint.setTextSize(25);

        winner_text.setColor(Color.WHITE);
        winner_text.setStyle(Paint.Style.FILL);
        winner_text.setAntiAlias(true);
        winner_text.setTextSize(65);




        player_1.setColor(getResources().getColor(rule.getPlayer_1_color()));
        player_1.setStyle(Paint.Style.FILL);
        player_1.setAntiAlias(true);

        player_2.setColor(getResources().getColor(rule.getPlayer_2_color()));
        player_2.setStyle(Paint.Style.FILL);
        player_2.setAntiAlias(true);

        /*player_1_turn.setColor(getResources().getColor(rule.getPlayer_1_color()));
        player_1_turn.setStyle(Paint.Style.FILL);
        player_1_turn.setAntiAlias(true);

        player_2_turn.setColor(getResources().getColor(rule.getPlayer_2_color()));
        player_2_turn.setStyle(Paint.Style.FILL);
        player_2_turn.setAntiAlias(true);*/







        //canvas.drawCircle(275,(float) initial_y-100 , radius-10, paint);
        //float[][] circles=new float[7][6];
        //float[] circles=new float[42];

        //rectPaint.setStyle(Paint.Style.FILL);
        //rectPaint.setColor(Color.BLUE);
        //canvas.drawRect(0,0,radiusX+10,initial_x+10*radiusX,rectPaint);
        //RectF r=new RectF(radiusX-10,radiusX-10,radiusX+10,initial_x+10*radiusX);


        //radiusX=((xPos.cEndStartDiff/6.00f)/2.00f);
        //double end_x=initial_x+13*radiusX;
        Log.d("msg","RadiusX - "+radiusX);
        //canvas.drawCircle(50,(float) initial_y-100 , radiusX-10, paint);
        //for(float j= initial_y;j<end_y;j+=2*radiusX) {
        //for (float i =(float) initial_x; i <= end_x; i += 2 * radiusX) {
        canvas.drawText("YOU", locationGridArray.circles.get(locationGridArray.loc[0][0]).getLoc_x()-15, (float)2.5*radiusX, text_paint);
        if (rule.getTurn()==Player.PLAYER_1) canvas.drawCircle(locationGridArray.circles.get(locationGridArray.loc[0][0]).getLoc_x(), radiusX-5, radiusX-10, white_circle_paint);
        canvas.drawCircle(locationGridArray.circles.get(locationGridArray.loc[0][0]).getLoc_x(), radiusX-5, rule.getTurn()==Player.PLAYER_1? radiusX-15:radiusX-30, player_1 );
        //canvas.drawText("FRIEND", locationGridArray.circles.get(locationGridArray.loc[0][6]).getLoc_x()-60, 3*radiusX, text_paint);
        if (rule.getTurn()==Player.PLAYER_2) canvas.drawCircle(locationGridArray.circles.get(locationGridArray.loc[0][6]).getLoc_x(), radiusX-5, radiusX-10, white_circle_paint);
        canvas.drawCircle(locationGridArray.circles.get(locationGridArray.loc[0][6]).getLoc_x(), radiusX-5, rule.getTurn()==Player.PLAYER_2? radiusX-15:radiusX-30, player_2 );
        canvas.drawText("FRIEND", locationGridArray.circles.get(locationGridArray.loc[0][6]).getLoc_x()-50, (float)2.5*radiusX, text_paint);
        if (rule.getWin())canvas.drawText(rule.getWinner()==Player.PLAYER_1? "YOU WIN":"FRIEND WINS", locationGridArray.circles.get(locationGridArray.loc[0][rule.getWinner()==Player.PLAYER_2? 1:2]).getLoc_x(), locationGridArray.circles.get(locationGridArray.loc[0][2]).getLoc_y()-100, winner_text );


        for(int j=0;j<6;j++)
        {
            for(int i=0;i<7;i++)
            {
                int player=locationGridArray.circles.get(locationGridArray.loc[j][i]).getPlayer();
                if(player==Player.PLAYER_1)
                {
                    canvas.drawCircle(locationGridArray.circles.get(locationGridArray.loc[j][i]).getLoc_x(), locationGridArray.circles.get(locationGridArray.loc[j][i]).getLoc_y(), radiusX-10, white_circle_paint);
                    canvas.drawCircle(locationGridArray.circles.get(locationGridArray.loc[j][i]).getLoc_x(), locationGridArray.circles.get(locationGridArray.loc[j][i]).getLoc_y(), locationGridArray.circles.get(locationGridArray.loc[j][i]).getWinning_circle()?radiusX-15:radiusX-10, player_1);

                }
                else if(player==Player.PLAYER_2)
                {
                    canvas.drawCircle(locationGridArray.circles.get(locationGridArray.loc[j][i]).getLoc_x(), locationGridArray.circles.get(locationGridArray.loc[j][i]).getLoc_y(), radiusX-10, white_circle_paint);
                    canvas.drawCircle(locationGridArray.circles.get(locationGridArray.loc[j][i]).getLoc_x(), locationGridArray.circles.get(locationGridArray.loc[j][i]).getLoc_y(), locationGridArray.circles.get(locationGridArray.loc[j][i]).getWinning_circle()?radiusX-15:radiusX-10, player_2);
                }
                else if(player==0)
                {
                    canvas.drawCircle(locationGridArray.circles.get(locationGridArray.loc[j][i]).getLoc_x(), locationGridArray.circles.get(locationGridArray.loc[j][i]).getLoc_y(), radiusX-10, white_circle_paint);
                }

            }

        }




                    /*Log.i("msg", "i - "+i);
                    Log.i("msg", "j - "+j);
                    circle_loc[0]=i;
                    circle_loc[1]=j;
                    Log.i("msg", "circle_loc[0] - "+ circle_loc[0]);
                    Log.i("msg", "circle_loc[1]- "+ circle_loc[1]);

                    circles.add(circle_loc);*/
        //Log.i("msg", "circles.get(0)[0] - "+ circles.get(0)[0]);
        //Log.i("msg", "circles.get(0)[1] - "+ circles.get(0)[1]);
                    /*Location l=new Location();
                    l.setLoc_x(i);
                    l.setLoc_y(j);
                    circles.add(l);*/

        //}
        //}






        test_paint.setStyle(Paint.Style.FILL);
        test_paint.setColor(Color.RED);
        test_paint.setAntiAlias(true);
        //Log.d("msg","circles.get(loc[0][0]).loc_x - "+ circles.get(loc[0][0]).getLoc_x());
        //canvas.drawCircle(LocationGridArray.circles.get(loc[0][1]).getLoc_x(), LocationGridArray.circles.get(loc[0][1]).getLoc_y(),radiusX-10, test_paint);
            /*Log.i("msg", "circles.get(0)[0]"+ circles.get(41)[0]);
            Log.i("msg", "circles.get(0)[1]"+ circles.get(41)[1]);
            */

        //canvas.drawCircle(width/8f,height/2.2f,radius,paint);
        // canvas.drawCircle(width/4f, height/4f, radius, paint);
        //canvas.drawCircle((width/4f)+2*radius, height/4f, radius, paint);


    }

    float x,y, width_ratio;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        //LocationGridArray locationGridArray=new LocationGridArray();
        //locationGridArray.resetGrid();
        Log.d("msg", "over here")
;        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x = event.getX();
            y = event.getY();

            width_ratio=getWidth()/x;

            Log.d("msg", "ontouchevent width_ratio - "+width_ratio);
            if (!rule.getWin()) {
                if (y > 415) {
                    if (width_ratio <= 50 && width_ratio >= 8)

                        gamePlayController.dropBalls(0, rule.getTurn());

                        //invalidate();
                    else if (width_ratio <= 5.5 && width_ratio >= 4.0)
                        gamePlayController.dropBalls(1, rule.getTurn());

                    else if (width_ratio <= 3 && width_ratio >= 2.5)
                        gamePlayController.dropBalls(2, rule.getTurn());

                    else if (width_ratio <= 2.2 && width_ratio >= 1.8)
                        gamePlayController.dropBalls(3, rule.getTurn());

                    else if (width_ratio <= 1.6 && width_ratio >= 1.45)
                        gamePlayController.dropBalls(4, rule.getTurn());
                    else if (width_ratio <= 1.3 && width_ratio >= 1.2)
                        gamePlayController.dropBalls(5, rule.getTurn());
                    else if (width_ratio <= 1.15 && width_ratio >= 1)
                        gamePlayController.dropBalls(6, rule.getTurn());

                    if(gamePlayController.checkWin()==1)
                    {
                        Log.d("msg","Player 1 Wins");
                        rule.setWin(true);
                        rule.setWinner(1);

                        ta_da.start();
                    }else if (gamePlayController.checkWin()==2)
                    {
                        Log.d("msg","Player 2 Wins");
                        rule.setWin(true);
                        rule.setWinner(2);
                        ta_da.start();
                    }


                    invalidate();


                }
            }
        }










        //Log.d("msg","Coordinates - ("+x+", "+y+")");
        //Log.d("msg","width/x - "+getWidth()/x);


        //invalidate();
        return super.onTouchEvent(event);



    }







}



