package com.mgd.mgd.Buttons;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

import com.mgd.mgd.Collision;
import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.EntityBase;
import com.mgd.mgd.EntityManager;
import com.mgd.mgd.R;
import com.mgd.mgd.SampleGame;
import com.mgd.mgd.TouchManager;

/**
 * Created by 161832Q on 7/1/2018.
 */

public class PauseButton implements EntityBase {
    private Bitmap bmp = null;
    private boolean isDone = false;
    private int xPos, yPos;

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
        bmp = ResourceHandler.Instance.GetBitmap(R.drawable.pause);
        xPos = 100;
        yPos = 100;
    }

    @Override
    public void Update(float _dt) {
        if (TouchManager.Instance.IsDown())
        {
            // Check collision here!!!
            float imgRadius = bmp.getHeight() * 0.5f;
            if (Collision.SphereSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius))
            {
                // Button clicked!
                SampleGame.Instance.SetIsPaused(!SampleGame.Instance.GetIsPaused());
            }
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

    public static PauseButton Create()
    {
        PauseButton result = new PauseButton();
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
