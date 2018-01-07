package com.mgd.mgd;

import android.graphics.Canvas;
import android.provider.MediaStore;
import android.view.SurfaceView;

import com.mgd.mgd.Common.GameObjectManager;
import com.mgd.mgd.Common.GenericRenderable;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.Common.Vector3;
import com.mgd.mgd.Components.Collision.CollisionManager;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Enemy.EnemyManager;

// Remark: Too lazy to change classname

public class SampleGame{
    public final static SampleGame Instance = new SampleGame();
    float timer = 0.0f;
    private boolean isPaused = false;

    Player player = Player.Instance;

    private SampleGame()
    {
    }

    public void Init(SurfaceView _view)
    {
      EntityManager.Instance.Init(_view);
        ResourceHandler.Instance.Init(_view);

        MediaManager.Instance.Init(_view.getContext());
        MediaManager.Instance.PlayBGMusic(R.raw.yaruta, true);
        //MediaManager.Instance.PlaySound(R.raw.blast);
        EnemyManager.Instance.Init();

        player.Init();

      //create static objs
        GenericRenderable renderable = new GenericRenderable();
        renderable.Init();

/*        for(int i =0;i<4;++i) {
            EnemyManager.Instance.SpawnEnemy(EnemyManager.EnemyType.BOTULISM, new Vector3(0,0,0));
        }*/
    }

    public void Update(float _deltaTime)
    {

        EntityManager.Instance.Update(_deltaTime);
        GameObjectManager.Instance.Update(_deltaTime);
        EnemyManager.Instance.Update(_deltaTime);
        //Collision Update
        CollisionManager.instance.Update(_deltaTime);

        //Update Media
        MediaManager.Instance.Update(_deltaTime);
    }

    protected void Render(Canvas _canvas)
    {
        RenderManager.Instance.Render(_canvas);
        EntityManager.Instance.Render(_canvas);
    }

    public boolean GetIsPaused() {return isPaused;}
    public void SetIsPaused(boolean p) {isPaused = p;}
}

