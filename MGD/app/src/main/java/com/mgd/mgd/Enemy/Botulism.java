package com.mgd.mgd.Enemy;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;

/**
 * Created by 161832Q on 3/12/2017.
 */

// PuppetMaster
public class Botulism extends Enemy {

    public Botulism() {}

    @Override
    public void Init() {
        Transform transform = new Transform();
        //transform.Init();
        //transform.SetScale(50,50);
        this.components.add(transform);

        Render render = new Render();
        render.Init();
        //render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.settings), transform);
        this.components.add(render);
        RenderManager.Instance.AddRenderable(render);

        //add states here



        attack = 30;
        movespeed = 15;
        health = 50;
    }

    @Override
    public void Update(double dt) {
        sm.Update(dt);

        if(health <= 0)
            SetIsDead(true);
    }

}
