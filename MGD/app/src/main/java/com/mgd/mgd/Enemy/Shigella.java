package com.mgd.mgd.Enemy;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.Common.Vector3;
import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;
import com.mgd.mgd.States.StateAttack;
import com.mgd.mgd.States.StateMove;
import com.mgd.mgd.States.StateRandomMove;

import java.util.Random;

/**
 * Created by 161832Q on 3/12/2017.
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
        transform.SetScale(20,20);
        this.components.put("transform", transform);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.shigella), transform);
        this.components.put("render", render);
        RenderManager.Instance.AddRenderable(render);

        //add variables here
        movespeed = 50;
        health = 60;
        attack = 50;
        attackRange = 10;
        detectRange = 60;
        attackspeed = 2.f;
        transitionOffset = 10.f;
        name = "shigella";

        //add states here
        sm.AddState(new StateMove("move",this));
        sm.AddState(new StateRandomMove("randommove",this));
        sm.AddState(new StateAttack("attack",this));
        sm.SetNextState("randommove");
    }

    @Override
    public void Update(double dt) {
        sm.Update(dt);

        if(health <= 0)
            SetIsDead(true);
    }



}
