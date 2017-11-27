package com.mgd.mgd;

import android.graphics.Canvas;
import android.view.SurfaceView;

// Remark: Too lazy to change classname

public class SampleGame{
    public final static SampleGame Instance = new SampleGame();
    float timer = 0.0f;

    private SampleGame()
    {
    }

    public void Init(SurfaceView _view)
    {
      EntityManager.Instance.Init(_view);
    }

    public void Update(float _deltaTime)
    {
        timer += _deltaTime;
        if(timer > 1.0f) {
            SampleEntity.Create();
            timer = 0.0f;
        }

        EntityManager.Instance.Update(_deltaTime);
    }

    protected void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
    }
}

