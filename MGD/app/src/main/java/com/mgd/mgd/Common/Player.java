package com.mgd.mgd.Common;

import android.graphics.PointF;
import android.util.Log;
import android.util.Pair;

import com.mgd.mgd.Components.Collision.Collider;
import com.mgd.mgd.Components.Collision.PlayerResponse;
import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.ScoreSystem;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;
import com.mgd.mgd.TouchManager;

public class Player extends GameObject{
    public final static Player Instance = new Player();
    private Player() {}
    public int health = 100;
    final int maxHealth = 100;

    @Override
    public void Init(){
        Transform transform = new Transform();
        transform.Init();
        transform.SetScale(50,50);
        this.components.put("transform", transform);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.settings), transform);
        this.components.put("render", render);
        RenderManager.Instance.AddRenderable(render);

        //init collision
        PlayerResponse playerResponse = new PlayerResponse();
        playerResponse.Init();

        Collider collider = new Collider();
        collider.Init();
        collider.response = playerResponse;
        this.components.put("collider", collider);


        ScoreSystem score = new ScoreSystem();
        score.Init();
        this.components.put("score",score);

        GameObjectManager.Instance.AddGo(this);
    }

    @Override
    public void Update(double dt) {

        UpdateMovement(dt);

        ScoreSystem score = (ScoreSystem) this.components.get("score");
        Log.i("Score", String.valueOf(score.GetScore()));
        Log.i("Combo",String.valueOf(score.GetCombo()));
    }

    @Override
    public void Destroy(){

    }


    private void UpdateMovement(double dt){

        if (TouchManager.Instance.IsDown())
        {
            PointF touch = new PointF(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY());

            //if touch screen, move char
        }
    }

    public void SetHealth(int hp) { health = hp;}

    public int GetHealth() {return health;}

    // used for rendering hp bar
    public float GetPercentageHealth() {
        return health / maxHealth;
    }
}


