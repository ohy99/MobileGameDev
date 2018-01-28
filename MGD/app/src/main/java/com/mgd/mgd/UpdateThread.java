package com.mgd.mgd;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class UpdateThread extends Thread
{
    static final long targetFPS = 60;

    private GameView view = null;
    private SurfaceHolder holder = null;
    private boolean isRunning = false;

    public UpdateThread(GameView _view)
    {
        view = _view;
        holder = _view.getHolder();

        // init here
        SampleGame.Instance.Init(view);
    }

    public boolean IsRunning() {return isRunning;}

    public void Initialize() {isRunning = true;}
    public void Terminate() {isRunning = false;}

    @Override
    public void run()
    {
        long framePerSec = 1000 / targetFPS;
        long startTime = 0;
        long prevTime = System.nanoTime();

        while(isRunning)
        {
            // Update

            /*-- Frame Limiter/Time Counter--*/
            startTime = System.currentTimeMillis();
            long currTime = System.nanoTime();
            float dt = (float)((currTime - prevTime)/1000000000.0f);
            prevTime = currTime;

            SampleGame.Instance.Update(dt);

            // Render
            Canvas canvas = holder.lockCanvas(null);
            if(canvas != null)
            {
                // to prevent 2 threads from rendering at same time
                synchronized (holder)
                {
                    // render whole screen black
                    canvas.drawColor(Color.BLACK);

                    //render other stuff here
                    SampleGame.Instance.Render(canvas);
                }
                holder.unlockCanvasAndPost(canvas);
            }

            // Post Update/Render
            try {
                long sleepTime = framePerSec - (System.currentTimeMillis() - startTime);

                if(sleepTime > 0)
                    sleep(sleepTime);
            }
            catch (InterruptedException e) {
                Terminate();
            }

            // here can be statemanager update





        }

        SampleGame.Instance.OnDestroy();
    }
}


