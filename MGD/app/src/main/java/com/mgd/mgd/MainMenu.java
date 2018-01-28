package com.mgd.mgd;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
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


        boolean loadedBefore = false;

        SharedPreferences settings = getSharedPreferences("SETTINGS", MODE_PRIVATE);
        loadedBefore = settings.getBoolean("FIRST_RUN", false);
        if (!loadedBefore) {
            // do the thing for the first time
            settings = getSharedPreferences("SETTINGS", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("FIRST_RUN", true);
            editor.apply();

            SharedPreferences.Editor score = getSharedPreferences("score", MODE_PRIVATE).edit();
            score.putInt("num", 0);
            score.apply();


        } else {
            // other time your app loads
        }

    }

    @Override
    //Invoke a callback on clicked event on a view
    public void onClick(View v)
    {
        Intent intent = new Intent();

        if(v == btn_start) {
            intent.setClass(MainMenu.this, GamePage.class);
            startActivity(intent);
            MainMenu.this.finish();
        }

        if(v == btn_facebook) {

        }

        if (v == btn_settings) {

        }

        if(v == btn_highscore) {
            intent.setClass(MainMenu.this, ScorePage.class);
            startActivity(intent);
            MainMenu.this.finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

