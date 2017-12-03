package com.mgd.mgd;

import android.graphics.Canvas;
import android.view.SurfaceView;

import com.mgd.mgd.Common.GameObject;
import com.mgd.mgd.Common.GameObjectManager;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.Components.Collision.CollisionManager;
import com.mgd.mgd.Components.RenderManager;

// Remark: Too lazy to change classname

public class SampleGame{
    public final static SampleGame Instance = new SampleGame();
    float timer = 0.0f;

    Player player = Player.Instance;

    private SampleGame()
    {
    }

    public void Init(SurfaceView _view)
    {
      EntityManager.Instance.Init(_view);
        ResourceHandler.Instance.Init(_view);

        player.Init();
    }

    public void Update(float _deltaTime)
    {
        timer += _deltaTime;
        if(timer > 1.0f) {
            SampleEntity.Create();
            timer = 0.0f;
        }

        EntityManager.Instance.Update(_deltaTime);
        GameObjectManager.Instance.Update(_deltaTime);

        //Collision Update
        CollisionManager.instance.Update(_deltaTime);
    }

    protected void Render(Canvas _canvas)
    {
        RenderManager.Instance.Render(_canvas);
        EntityManager.Instance.Render(_canvas);
    }
}

