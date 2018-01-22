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
import com.mgd.mgd.Enemy.EnemyManager;

// Remark: Too lazy to change classname

public class SampleGame {
    public final static SampleGame Instance = new SampleGame();
    //public final static String SHARED_PREF_ID = "GameSaveFile"; //game save file id

    float timer = 0.0f;
    private boolean isPaused = false;
    private boolean gameover = false;

    //SharedPreferences sharedPref = null;
    //SharedPreferences.Editor editor = null;

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
    }

    public void Update(float _deltaTime) {

        EntityManager.Instance.Update(_deltaTime);

        if (((Health)player.GetComponent("hp")).GetHpPercentage() <= 0) {
            gameover = true;
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


    //public void SetIntInSave(String key, int value) {
      //  if(editor == null)
        //    return;

        //save data here
       // editor.putInt(key, value);
    //}

    //public int GetIntFromSave(String key) {

        //attempt to get using key, if fail provide default variable based on our input
      //  return sharedPref.getInt(key, 0);
    //}

    //public void SaveEditBegin() {
        //safety check, make sure no one else is doing an edit
      //  if(editor != null)
//            return;

        // start the editng by providing editor
  //      editor = sharedPref.edit();
    //}

    //public void SaveEditEnd() {
        // safety check, only allow if editor is available
      //  if(editor == null)
        //    return;

//        editor.commit();
  //      editor = null; //clean up editor so editor can work
    //}
}

