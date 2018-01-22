package com.mgd.mgd.Common;

import android.graphics.PointF;
import android.util.Log;
import android.util.Pair;

import com.mgd.mgd.Components.Collision.Collider;
import com.mgd.mgd.Components.Collision.CollisionManager;
import com.mgd.mgd.Components.Collision.PlayerResponse;
import com.mgd.mgd.Components.ComponentBase;
import com.mgd.mgd.Components.Health;
import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.ScoreSystem;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.MediaManager;
import com.mgd.mgd.R;
import com.mgd.mgd.TouchManager;

import java.util.Map;

public class Player extends GameObject{
    public final static Player Instance = new Player();
    private Player() {}


    PointF prevPoint = new PointF(0,0);

    @Override
    public void Init(){
        Transform transform = new Transform();
        transform.Init();
        transform.SetScale(15,15);
        transform.SetPosition(15,15,2);
        this.components.put("transform", transform);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.player), transform);
        this.components.put("render", render);
        RenderManager.Instance.AddRenderable(render);

        Health hp = new Health();
        hp.Init();
        hp.InitHp(100);
        hp.SetRelativePos(transform.GetPosition(), new PointF(0, transform.GetScale().y * 0.5f + 2));
        this.components.put("hp", hp);

        //init collision
        PlayerResponse playerResponse = new PlayerResponse();
        playerResponse.Init();
        playerResponse.Init(hp);

        Collider collider = new Collider();
        collider.Init();
        collider.response = playerResponse;
        collider.transform=transform;
        this.components.put("collider", collider);
        CollisionManager.instance.addCollider(collider, this);

        ScoreSystem score = new ScoreSystem();
        score.Init();
        this.components.put("score",score);




        GameObjectManager.Instance.AddGo(this);

    }

    @Override
    public void Update(double dt) {

        for(Map.Entry<String, ComponentBase> e : components.entrySet())
        {
            e.getValue().Update(dt);
        }


        UpdateMovement(dt);
    }

    @Override
    public void Destroy(){
        Health hp = (Health) GetComponent("hp");
        hp.Destroy();
    }


    private void UpdateMovement(double dt){

        if (TouchManager.Instance.IsDown())
        {
            PointF touch = new PointF(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY());
            //inverse the y
            PointF canvasSize = new PointF(RenderManager.Instance.getCanvasWidth(), RenderManager.Instance.getCanvasHeight());
            touch.y = canvasSize.y - touch.y;
            float ratio = canvasSize.x / canvasSize.y;
            float worldwidth = ratio * 100.f;
            //if touch screen, move char

            //shoot
            Log.i("touch", String.valueOf(touch.y));
            Transform transform = (Transform) this.components.get("transform");
            PointF dir = new PointF();
            PointF pos = new PointF(transform.GetPosition().x, transform.GetPosition().y);
            PointF touchWorld = new PointF(touch.x / canvasSize.x * worldwidth, touch.y / canvasSize.y * 100.f);
            dir.x = -pos.x + touchWorld.x;
            dir.y = -pos.y + touchWorld.y;
            double len = Math.sqrt((double) (dir.x * dir.x) + (double) (dir.y * dir.y));
            dir.x = dir.x / (float)len;
            dir.y = dir.y / (float)len;
            //transform.SetDir(dir.x, dir.y);

            //shoot
            Projectiles proj = Projectiles.Create(Projectiles.ProjectileType.BULLET, new Vector3(transform.GetPosition().x, transform.GetPosition().y, 2), dir);
//            proj.Init();
//            proj.Set(new Vector3(transform.GetPosition().x, transform.GetPosition().y, 2), dir, new PointF(7,7));


            //Play Sound
            MediaManager.Instance.PlaySound(R.raw.blast);
        }

        if (TouchManager.Instance.HasTouch())
        {
            PointF touch = new PointF(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY());
            //inverse the y
            PointF canvasSize = new PointF(RenderManager.Instance.getCanvasWidth(), RenderManager.Instance.getCanvasHeight());
            touch.y = canvasSize.y - touch.y;
            float ratio = canvasSize.x / canvasSize.y;
            float worldwidth = ratio * 100.f;
            //if touch screen, move char

            //shoot
            Transform transform = (Transform) this.components.get("transform");
            PointF dir = new PointF();
            PointF pos = new PointF(transform.GetPosition().x, transform.GetPosition().y);
            PointF touchWorld = new PointF(touch.x / canvasSize.x * worldwidth, touch.y / canvasSize.y * 100.f);

            if (!prevPoint.equals(0,0))
            {
                PointF displacement = new PointF();
                displacement.x = -prevPoint.x + touchWorld.x;
                displacement.y = -prevPoint.y + touchWorld.y;


                Vector3 pos3 = transform.GetPosition();
                pos3.x += displacement.x * 10.f * dt;
                pos3.y += displacement.y * 10.f * dt;

                Log.i("MOVE", String.valueOf(displacement.x) + " , " + String.valueOf(displacement.y));
            }
            prevPoint.set(touchWorld);
        }
        else
            prevPoint.set(0,0);

    }

}


