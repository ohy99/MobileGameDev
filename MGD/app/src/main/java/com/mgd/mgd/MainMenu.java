package com.mgd.mgd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainMenu extends Activity implements OnClickListener {

    //Define button as an obj
    private Button btn_start;
    private Button btn_settings;
    private Button btn_highscore;
    private Button btn_facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_menu);
        //setContentView(new GameView(this));

        // set listener to button
        btn_start = (Button)findViewById(R.id.play_button);
        btn_start.setOnClickListener(this);
        btn_settings = (Button)findViewById(R.id.settings_button);
        btn_settings.setOnClickListener(this);
        btn_highscore = (Button)findViewById(R.id.highscore_button);
        btn_highscore.setOnClickListener(this);
        btn_facebook = (Button)findViewById(R.id.facebook_button);
        btn_facebook.setOnClickListener(this);


    }

    @Override
    //Invoke a callback on clicked event on a view
    public void onClick(View v)
    {
        Intent intent = new Intent();

        if(v == btn_start) {
            intent.setClass(MainMenu.this, SplashScreen.class);
            startActivity(intent);
            //MainMenu.this.finish();
        }

        if(v == btn_facebook) {

        }

        if (v == btn_settings) {

        }

        if(v == btn_highscore) {

        }
    }
}

