package com.mgd.mgd.Enemy;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.Common.Vector3;
import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;
import com.mgd.mgd.States.StateAttack;
import com.mgd.mgd.States.StateIdle;
import com.mgd.mgd.States.StateMoving;

/**
 * Created by 161832Q on 3/12/2017.
 */

// Puppet
public class MiniBotulism extends Enemy {
    Vector3 spawnPos;
    public MiniBotulism(Vector3 spawnLocation) {
        spawnPos = spawnLocation;
    }

    @Override
    public void Init() {
        Transform transform = new Transform();
        transform.Init();
        transform.SetPosition(spawnPos.x, spawnPos.y, spawnPos.z + 1);
        transform.SetScale(10,10);
        this.components.put("transform" ,transform);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.botulism), transform);
        this.components.put("render", render);
        RenderManager.Instance.AddRenderable(render);

        // add variables here
        attack = 10;
        movespeed = 25;
        attackRange = 7;
        detectRange = 50;
        attackspeed = 1.5f;
        health = 100;
        name = "minibotulism";

        //add states here
    sm.AddState(new StateIdle("idle",this));
    sm.AddState(new StateMoving("moving",this));
    sm.AddState(new StateAttack("attack",this));
    sm.SetNextState("idle");



    }

    @Override
    public void Update(double dt) {
        sm.Update(dt);

        if(health <= 0)
            SetIsDead(true);
    }
}
