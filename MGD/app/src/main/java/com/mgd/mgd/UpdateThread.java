package com.mgd.mgd;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class UpdateThread extends Thread
{
    private GameView view = null;
    private SurfaceHolder holder = null;
    private boolean isRunning = false;

    public UpdateThread(GameView _view)
    {
        view = _view;
        holder = _view.getHolder();

        // init here
    }

    public boolean IsRunning() {return isRunning;}

    public void Initialize() {isRunning = true;}
    public void Terminate() {isRunning = false;}

    @Override
    public void run()
    {
        while(isRunning)
        {
            // here can be statemanager update

            Canvas canvas = holder.lockCanvas(null);
            if(canvas != null)
            {
                // to prevent 2 threads from rendering at same time
                synchronized (holder)
                {
                    // render whole screen black
                    canvas.drawColor(Color.BLACK);

                    //insert stuff here
                    // SampleGame::Instance.Render();
                }
                holder.unlockCanvasAndPost(canvas);
            }

            // should have something to limit framerate
        }
    }
}


