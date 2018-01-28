package com.mgd.mgd.Buttons;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.provider.MediaStore;
import android.view.SurfaceView;

import com.mgd.mgd.Collision;
import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.EntityBase;
import com.mgd.mgd.EntityManager;
import com.mgd.mgd.MediaManager;
import com.mgd.mgd.R;
import com.mgd.mgd.SampleGame;
import com.mgd.mgd.TouchManager;

/**
 * Created by 161832Q on 8/1/2018.
 */

public class MuteButton implements EntityBase {
    private Bitmap bmp = null;
    private boolean isDone = false;
    private int xPos, yPos;
    private float minX,minY,maxX,maxY;
    private boolean wasDown = false;
    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {
        bmp = ResourceHandler.Instance.GetBitmap(R.drawable.sound);
        xPos = 750;
        yPos = 450;
        minX = xPos - bmp.getWidth() * 0.5f;
        maxX = xPos + bmp.getWidth() * 0.5f;
        minY = yPos - bmp.getHeight() * 0.5f;
        maxY = yPos + bmp.getHeight() * 0.5f;
    }



    @Override
    public void Update(float _dt) {
        if (TouchManager.Instance.IsDown() && !wasDown)
        {
            // Check collision here!!!

            if(Collision.PointQuad(minX,minY,maxX,maxY,TouchManager.Instance.GetPosX(),TouchManager.Instance.GetPosY()))
            {
                // Button clicked!
                //MediaManager.Instance.ToggleSound();
                if(MediaManager.Instance.GetVolume() != 0.0) {
                    MediaManager.Instance.MuteSound(1);
                    MediaManager.Instance.MuteSound(0);
                    bmp = ResourceHandler.Instance.GetBitmap(R.drawable.mute);
                }
                else {
                    MediaManager.Instance.UnMuteSound(1);
                    MediaManager.Instance.UnMuteSound(0);
                    bmp = ResourceHandler.Instance.GetBitmap(R.drawable.sound);
                }
                wasDown= true;
            }
        }
        else if (!TouchManager.Instance.IsDown() && wasDown)
            wasDown = false;

        if(!SampleGame.Instance.GetIsPaused()) {
            isDone = true;
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null);
    }

    @Override
    public boolean IsInit() {
        return bmp != null;
    }

    @Override
    public int GetRenderLayer() {
        return 0;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    public static MuteButton Create()
    {
        MuteButton result = new MuteButton();
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
