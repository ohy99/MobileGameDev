package com.mgd.mgd.Enemy;

import android.graphics.PointF;
import android.util.Log;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.Components.Collision.Collider;
import com.mgd.mgd.Components.Collision.CollisionManager;
import com.mgd.mgd.Components.Collision.PlayerResponse;
import com.mgd.mgd.Components.Collision.ProjectileResponse;
import com.mgd.mgd.Components.ComponentBase;
import com.mgd.mgd.Components.Health;
import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;
import com.mgd.mgd.States.StateMove;
import com.mgd.mgd.States.StateRandomMove;
import com.mgd.mgd.States.StateSummon;

import java.util.Map;
import java.util.Random;

/**
 * Created by 161832Q on 3/12/2017.
 */

// PuppetMaster
public class Botulism extends Enemy {

    public Botulism() {}
    Random r = new Random();
    @Override
    public void Init() {
        Transform transform = new Transform();
        transform.Init();
        transform.SetPosition(150,r.nextInt(20),2);
        transform.SetScale(10,10);
        this.components.put("transform" ,transform);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.botulism), transform);
        this.components.put("render", render);
        RenderManager.Instance.AddRenderable(render);

        Health hp = new Health();
        hp.Init();
        hp.InitHp(50);
        hp.SetRelativePos(transform.GetPosition(), new PointF(0, transform.GetScale().y * 0.5f + 2.0f));
        this.components.put("hp", hp);

        PlayerResponse response = new PlayerResponse();
        response.Init();
        response.Init(hp);

        Collider collider = new Collider();
        collider.Init();
        collider.transform = transform;
        collider.response = response;
        this.components.put("collider", collider);
        CollisionManager.instance.addCollider(collider, this);


        // add variables here
        attack = 30;
        movespeed = 1;
        attackRange = 7;
        detectRange = 70;
        attackspeed = 1.5f;
        //health = 100;
        transitionOffset = 10;
        name = "botulism";

        //add states here
        sm.AddState(new StateSummon("summon",this));
        sm.AddState(new StateMove("move",this));
        sm.SetNextState("summon");


    }

    @Override
    public void Update(double dt) {
        sm.Update(dt);

        for (Map.Entry<String, ComponentBase> e : components.entrySet())
            e.getValue().Update(dt);

        Health hp = (Health) GetComponent("hp");
        if (hp.GetHpPercentage() <= 0.f)
            this.SetIsDead(true);

    }


    @Override
    public  void Destroy(){
        RenderManager.Instance.RemoveRenderable((Render) this.components.get("render"));
        CollisionManager.instance.removeCollider((Collider) this.components.get("collider"), this);
        Health hp = (Health) GetComponent("hp");
        hp.Destroy();

        Log.i("Boi", "Deleted");
    }
}
