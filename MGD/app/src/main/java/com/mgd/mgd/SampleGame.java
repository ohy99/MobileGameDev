package com.mgd.mgd;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceView;
import android.view.View;
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
import com.mgd.mgd.Dialog.ScoreInputDialogFragment;
import com.mgd.mgd.Enemy.EnemyManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Remark: Too lazy to change classname

public class SampleGame {
    public final static SampleGame Instance = new SampleGame();
    //public final static String SHARED_PREF_ID = "GameSaveFile"; //game save file id

    Context context = null;

    float timer = 0.0f;
    private boolean isPaused = false;
    public boolean gameover = false;

    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;

    Player player = Player.Instance;
    private boolean gameSaved = false;

    ScoreInputDialogFragment scoreInputDialogFragment = null;
    //private boolean touchDown;

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
        context = _view.getContext();

        EntityManager.Instance.Init(_view);
        ResourceHandler.Instance.Init(_view);

        MediaManager.Instance.Init(_view.getContext());
        MediaManager.Instance.PlayBGMusic(R.raw.yaruta, true);
        //MediaManager.Instance.PlaySound(R.raw.blast);
        EnemyManager.Instance.Init();

        player.Init();

        //create button
        PauseButton.Create();


        //create static objs
        GenericRenderable renderable = new GenericRenderable();
        renderable.Init();

/*        for(int i =0;i<4;++i) {
            EnemyManager.Instance.SpawnEnemy(EnemyManager.EnemyType.BOTULISM, new Vector3(0,0,0));
        }*/

        sharedPref = _view.getContext().getSharedPreferences("score", Context.MODE_PRIVATE);
        gameSaved = false;
        scoreInputDialogFragment = new ScoreInputDialogFragment();
        //scoreInputDialogFragment.InitDialogTextBox(_view.getContext());
        //touchDown = false;

        isPaused=  false;
        gameover = false;
    }

    public void Update(float _deltaTime) {

        EntityManager.Instance.Update(_deltaTime);

        if (((Health)player.GetComponent("hp")).GetHpPercentage() <= 0) {
            gameover = true;

//            this.SaveScore((ScoreSystem) player.GetComponent("score"));
//
//            Intent intent = new Intent();
//            intent.setClass(GamePage.Instance,PostGameScreen.class);
//            startActivity(intent);
//            GamePage.Instance.finish();
        }

        if (gameover)
        {
            if (!scoreInputDialogFragment.IsShown)
            {
                scoreInputDialogFragment.show(GamePage.Instance.getFragmentManager(), "scoreInput");
                scoreInputDialogFragment.IsShown = true;
            }
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


    public void SaveScore(ScoreSystem score, String inputName)
    {
        if (gameSaved)
            return;
        editor = sharedPref.edit();

        int numOfSaves = sharedPref.getInt("num", 0);
//        Set<String> stringSet = new HashSet<String>();
//        stringSet.add("name");
//        stringSet.add(String.valueOf(score.GetScore()));
//        editor.putStringSet(String.valueOf(numOfSaves), stringSet);//0 , 1, 2, 3, 4,

        editor.putString("name" + String.valueOf(numOfSaves), inputName);
        editor.putInt("score" + String.valueOf(numOfSaves), score.GetScore());
        editor.putInt("num", ++numOfSaves);

        Log.i("score", String.valueOf(score.GetScore()));
        //this does it in background, something like new thread?
        editor.apply();
        //this does it in the same main thread so will lag vv
        //editor.commit();
        editor = null;

        gameSaved = true;
    }


    public void OnDestroy()
    {
        RenderManager.Instance.RemoveAll();
        ParticleManager.Instance.RemoveAll();
        CollisionManager.instance.RemoveAll();
        EnemyManager.Instance.RemoveAll();
        GameObjectManager.Instance.RemoveAll();
        MediaManager.Instance.RemoveAll();

        EntityManager.Instance.RemoveAllEntity();
    }

}


