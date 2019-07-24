package com.example.connect4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MenuViewActivity extends AppCompatActivity{



        private static float width, height;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_activity);
        final RelativeLayout relativeLayout=findViewById(R.id.menuView);

        final RadioGroup first_turn_RadioGroup =findViewById(R.id.first_turn);
        final RadioGroup choose_color_radioGroup=findViewById(R.id.choose_your_color);
        Button play_button=findViewById(R.id.play_button);



        ViewTreeObserver viewTreeObserver=relativeLayout.getViewTreeObserver();
        if(viewTreeObserver.isAlive())
        {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    height=relativeLayout.getHeight();
                    width=relativeLayout.getWidth();
                    Log.d("msg","viewHeight - "+height);
                    if(height!=0)
                        relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int first_turn_radioGroup = first_turn_RadioGroup.getCheckedRadioButtonId();
                int choose_color_radioButton=choose_color_radioGroup.getCheckedRadioButtonId();
                Rule rule=new Rule(first_turn_radioGroup,choose_color_radioButton);
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("height",height);
                intent.putExtra("width", width);
                intent.putExtra("rule", rule);
                startActivity(intent);


            }
        });

    }

    //public MenuView(Context context, AttributeSet attributeSet){super(context,attributeSet);}

    //public MenuView(Context context,AttributeSet attributeSet, int defStyleAttr){super(context, attributeSet, defStyleAttr);}


}
