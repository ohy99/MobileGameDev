package com.mgd.mgd;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.EventLog;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.mgd.mgd.Common.Constants;

public class GamePage extends Activity {
    // this is global access variable
    public static GamePage Instance = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new GameView(this));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        // update global access reference variable
        Instance = this;




    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        TouchManager.Instance.Update(x,y,event.getAction());

        return true;
    }
}
