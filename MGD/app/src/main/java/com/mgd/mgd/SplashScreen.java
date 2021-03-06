package com.mgd.mgd;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by MarcusTan
 */

public class SplashScreen extends Activity{

    Thread SplashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
//
//        SplashThread = new Thread()
//        {
//
//            @Override
//            public void run()
//            {
//                try{
//                    int waited = 0;
//                    while (waited < 2000){
//                        sleep(200);
//                        waited += 200;
//                        if (this.isInterrupted())
//                        {
//                            Log.i("splash", "interrupted");
//                            return;
//                        }
//                    }
//
//
//                }catch (InterruptedException e){
//                    Log.i("splash", "exception");
//                    return;
//                }
//
//                if (!this.isInterrupted())
//                {
//                    Intent intent = new Intent();
//                    intent.setClass(SplashScreen.this, MainMenu.class);
//                    SplashScreen.this.finish();
//                    startActivity(intent);
//                    Log.i("splashthread", "done");
//                }
//
//            }
//        };
//        SplashThread.start();


        //NOTE: that code up there is damn buggy. When I interrupt the splash thread below, there is macam another ghost SplashThread running, thus calling startActivity twice. (indicated by "Log.i("splashthread", "done")")
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();

        switch (action) {

            case MotionEvent.ACTION_DOWN:

                if (SplashThread != null)
                {
                    if (SplashThread.isAlive())
                        SplashThread.interrupt();
                    if (SplashThread.isInterrupted())
                        Log.i("splash", "dead");
                }

                //SplashThread.stop();
                Intent intent = new Intent();
                intent.setClass(SplashScreen.this, MainMenu.class);
                SplashScreen.this.finish();
                startActivity(intent);


                break;
        }

        return super.onTouchEvent(event);
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
    protected void onResume(){ super.onResume(); }

  @Override
    protected void onStop() {
      super.onStop();
  }

  @Override
    protected void onDestroy() {
      super.onDestroy();
  }


}

