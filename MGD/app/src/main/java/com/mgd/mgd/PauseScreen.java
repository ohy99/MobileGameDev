package com.mgd.mgd;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.view.SurfaceView;

import com.mgd.mgd.Collision;
import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.Dialog.PauseConfirmDialogFragment;
import com.mgd.mgd.EntityBase;
import com.mgd.mgd.EntityManager;
import com.mgd.mgd.GamePage;
import com.mgd.mgd.R;
import com.mgd.mgd.SampleGame;
import com.mgd.mgd.TouchManager;

/**
 * Created by MarcusTan on 7/1/2018.
 */

public class PauseScreen implements EntityBase {
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
        bmp = ResourceHandler.Instance.GetBitmap(R.drawable.pausescreen);
        xPos = 600;
        yPos = 400;
        minX = xPos - bmp.getWidth() * 0.5f;
        maxX = xPos + bmp.getWidth() * 0.5f;
        minY = yPos - bmp.getHeight() * 0.5f;
        maxY = yPos + bmp.getHeight() * 0.5f;
    }

    @Override
    public void Update(float _dt) {
        // a screen does not need to do anything
        if(!SampleGame.Instance.GetIsPaused())
            isDone = true;
    }

    @Override
    public void Render(Canvas _canvas) {
        float canvasX = _canvas.getWidth();
        float canvasY = _canvas.getHeight();
        _canvas.drawBitmap(bmp,-0.5f * canvasX  ,0 , null);
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

    public static PauseScreen Create()
    {
        PauseScreen result = new PauseScreen();
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
