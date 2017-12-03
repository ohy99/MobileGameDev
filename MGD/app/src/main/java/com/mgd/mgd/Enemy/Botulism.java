package com.mgd.mgd.Enemy;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;
import com.mgd.mgd.States.BotulismStates.StateSummon;
import com.mgd.mgd.States.StateAttack;
import com.mgd.mgd.States.StateMoving;

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
        transform.SetPosition(r.nextInt(100),0,r.nextInt(500));
        transform.SetScale(40,20);
        this.components.put("transform" ,transform);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.botulism), transform);
        this.components.put("render", render);
        RenderManager.Instance.AddRenderable(render);


        // add variables here
        attack = 30;
        movespeed = 15;
        attackRange = 7;
        detectRange = 40;
        attackspeed = 1.5f;
        health = 100;
        name = "botulism";

        //add states here
        sm.AddState(new StateSummon("summon",this));
        sm.AddState(new StateMoving("moving",this));
        sm.AddState(new StateAttack("attack",this));

    }

    @Override
    public void Update(double dt) {
        sm.Update(dt);

        if(health <= 0)
            SetIsDead(true);
    }

}
