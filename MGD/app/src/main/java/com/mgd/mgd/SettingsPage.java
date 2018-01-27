package com.mgd.mgd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class SettingsPage extends Activity implements OnClickListener {

    //Define button as an obj
    private Button btn_back;
    private Button btn_toggleSFX;
    private Button btn_toggleBGM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_settings);

        // set listener to button
        btn_back = (Button)findViewById(R.id.back_button);
        btn_back.setOnClickListener(this);
        btn_toggleBGM = (Button)findViewById(R.id.bgmtoggle_button);
        btn_toggleBGM.setOnClickListener(this);
        btn_toggleSFX = (Button)findViewById(R.id.sfxtoggle_button);
        btn_toggleSFX.setOnClickListener(this);
    }

    @Override
    //Invoke a callback on clicked event on a view
    public void onClick(View v)
    {
        Intent intent = new Intent();

        if(v == btn_back) {
            intent.setClass(SettingsPage.this, MainMenu.class);
            startActivity(intent);
            SettingsPage.this.finish();
        }

        if(v == btn_toggleSFX) {
            // change background img of button and mute/unmute

        }

        if(v == btn_toggleBGM) {

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


