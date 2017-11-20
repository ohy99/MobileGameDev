package com.mgd.mgd;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.SurfaceView;

// Remark: Too lazy to change classname

public class SampleGame{
    public final static SampleGame Instance = new SampleGame();
    float offset = 0.0f;
    Bitmap bmp;
    private SampleGame()
    {
    }

    public void Update(float _deltaTime)
    {
        offset += _deltaTime;
    }

    public void Init(SurfaceView _view)
    {
       bmp = BitmapFactory.decodeResource(_view.getResources(),R.drawable.facebook);
    }

    protected void Render(Canvas _canvas)
    {
        Matrix matrix = new Matrix();
        matrix.setTranslate(10, 10);
        matrix.setScale(0.5f, 0.5f);
        int currOffset = (int)(offset * 100.0f);
        _canvas.drawBitmap(bmp, matrix ,null);

    }
}