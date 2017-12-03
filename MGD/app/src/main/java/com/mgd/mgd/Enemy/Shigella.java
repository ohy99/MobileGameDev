package com.mgd.mgd.Enemy;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.ResourceHandler;
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

// Charger
public class Shigella extends Enemy {

    public Shigella() {}

    @Override
    public void Init() {
        Transform transform = new Transform();
        //transform.Init();
        //transform.SetScale(50,50);
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
        attackRange = 4;
        detectRange = 60;
        attackspeed = 2.f;
        name = "shigella";

        //add states here
        sm.AddState(new StateIdle("idle",this));
        sm.AddState(new StateAttack("attack",this));
        sm.AddState(new StateMoving("moving",this));
    }

    @Override
    public void Update(double dt) {
        sm.Update(dt);

        if(health <= 0)
            SetIsDead(true);
    }



}
