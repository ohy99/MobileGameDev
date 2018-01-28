package com.mgd.mgd;

import android.content.Context;
import android.content.Intent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView
{
    private SurfaceHolder holder = null;
    private UpdateThread updateThread = new UpdateThread(this);

    public GameView(final Context _context)
    {
        // do what the base surface view will do to setup
        super(_context);
        holder = getHolder();

        if(holder!= null)
            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    //INIT
                    // set boolean to loop
                    if(!updateThread.IsRunning())
                        updateThread.Initialize();

                    // kick start thread if not alive
                    if(!updateThread.isAlive())
                        updateThread.start();
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    // nothing to do here...
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    // cleanup
                    updateThread.Terminate();
                }
            });

    }

}