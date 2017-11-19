package com.mgd.mgd;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SplashScreen extends Activity implements OnClickListener {

    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        btn_start = (Button)findViewById(R.id.button);
        btn_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();

        if(v == btn_start)
        {
            intent.setClass(SplashScreen.this, MainMenu.class);
            startActivity(intent);
            //MainMenu.this.finish();
        }

    }
}
