package com.example.connect4;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

//import static com.example.connect4.LocationGridArray.circles;


public class MainActivity extends AppCompatActivity {

    // static Context app_context;
    private static float height, width;

    GameView game_View;
    LocationGridArray locationGrid_Array;
    GamePlayController game_playController;
    Context context;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        Intent intent=getIntent();
        height=intent.getFloatExtra("height", 0);
        width=intent.getFloatExtra("width", 0);
        Rule rule=(Rule)intent.getSerializableExtra("rule");
        LocationGridArray locationGridArray=new LocationGridArray(width, height,rule);
        GamePlayController gamePlayController=new GamePlayController(locationGridArray, rule);
        final MediaPlayer ta_da=MediaPlayer.create(this, R.raw.ta_da);

        locationGridArray.setUpGrid();
        final GameView gameView =new GameView(getApplicationContext(), ta_da, rule, locationGridArray, gamePlayController);

        game_View=gameView;
        context=this;
        locationGrid_Array=locationGridArray;
        game_playController=gamePlayController;
        /*Display display=getWindowManager().getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);
        width=size.x;
        height=size.y;
        Log.d("msg","size y - "+height);*/


        Log.d("msg","Reached above MyView");

        setContentView(gameView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);


        Log.d("msg","layout.getHeight() - "+height);
        //float height1=getCurrentFocus().getHeight();
        //Log.d("msg","height1 - "+height1);
        Log.d("msg","REACHED THE CHECKPOINT");






        //setContentView(R.layout.menu_activity);
        //setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {

            case android.R.id.home:
                show_dialog(R.string.back);
                break;
            case R.id.restart:
                show_dialog(R.string.restart);
                break;
            case R.id.undo_move:

                game_playController.undo_move(game_View);
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }



    private void show_dialog(final int msg)
    {

        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(msg==R.string.back)
                            //Activity#finish();
                            ((MainActivity)context).finish();




                        else
                        {
                            locationGrid_Array.resetGrid();
                            game_View.invalidate();
                        }
                    }
                }).show();

    }

    @Override
    public void onBackPressed() {


        show_dialog(R.string.back);

    }



}
