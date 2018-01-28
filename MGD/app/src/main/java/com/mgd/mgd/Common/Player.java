package com.mgd.mgd.Common;

import android.graphics.PointF;
import android.util.Log;
import android.util.Pair;

import com.mgd.mgd.Collision;
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

    class Joystick
    {
        PointF dir;
        PointF center;
        float maxRadius;

        Transform bg_transform = new Transform();
        Render bg_render = new Render();

        Transform front_transform = new Transform();
        Render front_render = new Render();

        public boolean active = false;

        public void Init (PointF center, float maxRadius)
        {
            this.center = center;
            this.maxRadius = maxRadius;
            this.dir = new PointF();

            bg_transform.Init();
            bg_transform.SetPosition(center.x, center.y, 5);
            bg_transform.SetScale(maxRadius, maxRadius);

            bg_render.Init();
            bg_render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.joystick_background), bg_transform);
            RenderManager.Instance.AddRenderable(bg_render);

            front_transform.Init();
            front_transform.SetPosition(center.x, center.y, 5.5f);
            front_transform.SetScale(maxRadius * 0.5f, maxRadius *0.5f);

            front_render.Init();
            front_render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.joystick_background), front_transform);
            RenderManager.Instance.AddRenderable(front_render);
        }

        public void UpdateValues(PointF newPos)
        {
            PointF dir = new PointF();
            dir.set(-center.x + newPos.x, -center.y + newPos.y);
            double length = Math.sqrt(dir.x * dir.x + dir.y * dir.y);
            if (length > maxRadius)
            {
                dir.set(dir.x / (float)length, dir.y / (float)length);
                dir.x = dir.x * maxRadius * 0.5f;
                dir.y = dir.y * maxRadius * 0.5f;
            }

            this.dir = dir;


            front_transform.SetPosition(center.x + dir.x, center.y + dir.y, 5.5f);
        }

        public void AddToRenderables()
        {
            RenderManager.Instance.AddRenderable(bg_render);
            RenderManager.Instance.AddRenderable(front_render);
        }

        public void RemoveFromRenderables()
        {
            RenderManager.Instance.RemoveRenderable(bg_render);
            RenderManager.Instance.RemoveRenderable(front_render);

            Log.i("JoyStick", "Destroyed");
        }

        public void Reset()
        {
            dir.set(0,0);
            front_transform.SetPosition(center.x, center.y, 5.5f);
        }
    };
    Joystick joystick;

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

        joystick = new Joystick();
        joystick.Init(new PointF(25, 20), 30);

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

        joystick.RemoveFromRenderables();
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


            if (Collision.SphereSphere(joystick.center.x, joystick.center.y, joystick.maxRadius, touchWorld.x, touchWorld.y, 0.5f))
            {
                joystick.active = true;
            }
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



            if (joystick.active)
            {
                joystick.UpdateValues(touchWorld);

                Vector3 vpos3 = transform.GetPosition();
                vpos3.x += joystick.dir.x * 3.f * (float)dt;
                vpos3.y += joystick.dir.y * 3.f * (float)dt;

            }


//            if (!prevPoint.equals(0,0))
//            {
//                PointF displacement = new PointF();
//                displacement.x = -prevPoint.x + touchWorld.x;
//                displacement.y = -prevPoint.y + touchWorld.y;
//
//
//                Vector3 pos3 = transform.GetPosition();
//                pos3.x += displacement.x * 10.f * dt;
//                pos3.y += displacement.y * 10.f * dt;
//
//                Log.i("MOVE", String.valueOf(displacement.x) + " , " + String.valueOf(displacement.y));
//            }
            prevPoint.set(touchWorld);
        }
        else
        {
            joystick.Reset();
            joystick.active = false;
            prevPoint.set(0,0);
        }


        //BOUNDS
        Transform transform = (Transform)GetComponent("transform");
        Vector3 pos = transform.GetPosition();
        PointF scale = transform.GetScale();
        if (pos.x < 0 +scale.x)
            pos.x = 0 + scale.x;
        else if (pos.x > Constants.worldWidth - scale.x)
            pos.x = Constants.worldWidth - scale.x;
        if (pos.y < 0 + scale.y)
            pos.y = 0 + scale.y;
        else if (pos.y > Constants.worldHeight - scale.y)
            pos.y = Constants.worldHeight - scale.y;

    }

}


