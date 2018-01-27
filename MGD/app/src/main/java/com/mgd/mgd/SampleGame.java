package com.mgd.mgd;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.mgd.mgd.Buttons.MuteButton;
import com.mgd.mgd.Buttons.PauseButton;
import com.mgd.mgd.Common.Constants;
import com.mgd.mgd.Common.GameObjectManager;
import com.mgd.mgd.Common.GenericRenderable;
import com.mgd.mgd.Common.ParticleManager;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.Common.Vector3;
import com.mgd.mgd.Components.Collision.CollisionManager;
import com.mgd.mgd.Components.Health;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.ScoreSystem;
import com.mgd.mgd.Enemy.EnemyManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Remark: Too lazy to change classname

public class SampleGame {
    public final static SampleGame Instance = new SampleGame();
    //public final static String SHARED_PREF_ID = "GameSaveFile"; //game save file id

    float timer = 0.0f;
    private boolean isPaused = false;
    private boolean gameover = false;

    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;

    Player player = Player.Instance;

    private SampleGame() {
    }

    public void Init(SurfaceView _view)
    {
        //sharedPref = GamePage.Instance.getSharedPreferences(SHARED_PREF_ID, 0);

        WindowManager wm = (WindowManager) _view.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        //Display display = _view.getDisplay();//getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        float worldHeight = 100.f;
        float worldWidth = worldHeight * ((float)width / (float)height);
        Constants.worldWidth = worldWidth;
        Constants.worldHeight = worldHeight;

        EntityManager.Instance.Init(_view);
        ResourceHandler.Instance.Init(_view);

        MediaManager.Instance.Init(_view.getContext());
        MediaManager.Instance.PlayBGMusic(R.raw.yaruta, true);
        //MediaManager.Instance.PlaySound(R.raw.blast);
        EnemyManager.Instance.Init();

        player.Init();

        //create button
        PauseButton.Create();
        MuteButton.Create();

        //create static objs
        GenericRenderable renderable = new GenericRenderable();
        renderable.Init();

/*        for(int i =0;i<4;++i) {
            EnemyManager.Instance.SpawnEnemy(EnemyManager.EnemyType.BOTULISM, new Vector3(0,0,0));
        }*/

        sharedPref = _view.getContext().getSharedPreferences("score", Context.MODE_PRIVATE);

    }

    public void Update(float _deltaTime) {

        EntityManager.Instance.Update(_deltaTime);

        if (((Health)player.GetComponent("hp")).GetHpPercentage() <= 0) {
            gameover = true;
            this.SaveScore((ScoreSystem) player.GetComponent("score"));
        }


        if(!isPaused && !gameover) {
            GameObjectManager.Instance.Update(_deltaTime);
            EnemyManager.Instance.Update(_deltaTime);
            //Collision Update
            CollisionManager.instance.Update(_deltaTime);

            ParticleManager.Instance.Update(_deltaTime);
        }

        //Update Media
        MediaManager.Instance.Update(_deltaTime);


    }

    protected void Render(Canvas _canvas) {
        RenderManager.Instance.Render(_canvas);
        EntityManager.Instance.Render(_canvas);
    }

    public boolean GetIsPaused() {
        return isPaused;
    }

    public void SetIsPaused(boolean p) {
        isPaused = p;
    }


    public void SaveScore(ScoreSystem score)
    {
        editor = sharedPref.edit();

        int numOfSaves = sharedPref.getInt("num", 0);
        Set<String> stringSet = new HashSet<String>();
        stringSet.add("name");
        stringSet.add(String.valueOf(score.GetScore()));
        editor.putStringSet(String.valueOf(numOfSaves), stringSet);//0 , 1, 2, 3, 4,
        editor.putInt("num", ++numOfSaves);


        //this does it in background, something like new thread?
        editor.apply();
        //this does it in the same main thread so will lag
        //editor.commit();
        editor = null;
    }

}

