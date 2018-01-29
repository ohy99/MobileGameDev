package com.mgd.mgd.Enemy;

import android.graphics.PointF;
import android.util.Log;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.Common.Vector3;
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
import com.mgd.mgd.States.StateAttack;
import com.mgd.mgd.States.StateMove;
import com.mgd.mgd.States.StateRandomMove;

import java.util.Map;
import java.util.Random;

/**
 * Created by MarcusTan on 3/12/2017.
 */

// Puppet
public class Shigella extends Enemy {
    Vector3 spawnPos;
    public Shigella(Vector3 spawn) {
        spawnPos = spawn;
    }

    Random r = new Random();
    @Override
    public void Init() {
        Transform transform = new Transform();
        transform.Init();
        transform.SetPosition(spawnPos.x,spawnPos.y,spawnPos.z + 0.5f);
        transform.SetScale(10,10);
        this.components.put("transform", transform);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.shigella), transform);
        this.components.put("render", render);
        RenderManager.Instance.AddRenderable(render);

        Health hp = new Health();
        hp.Init();
        hp.InitHp(10);
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

        //add variables here
        movespeed = 2;
        //health = 60;
        attack = 50;
        attackRange = 10;
        detectRange = 60;
        attackspeed = 1.f;
        transitionOffset = 10.f;
        name = "shigella";

        //add states here
        sm.AddState(new StateMove("move",this));
        sm.AddState(new StateRandomMove("randommove",this));
        sm.AddState(new StateAttack("attack",this));
        sm.SetNextState("move");
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
    public void Destroy(){
        RenderManager.Instance.RemoveRenderable((Render) this.components.get("render"));
        CollisionManager.instance.removeCollider((Collider) this.components.get("collider"), this);

        Log.i("Boi", "Deleted");
    }

}
