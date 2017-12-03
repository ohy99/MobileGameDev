package com.mgd.mgd.Enemy;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.Transform;

/**
 * Created by 161832Q on 3/12/2017.
 */

// Range
public class Listeria extends Enemy {

    public Listeria() {};

    @Override
    public void Init() {
        Transform transform = new Transform();
        //transform.Init();
        //transform.SetScale(50,50);
        this.components.put( "transform", transform);

        Render render = new Render();
        render.Init();
        //render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.settings), transform);
        this.components.put("render", render);
        RenderManager.Instance.AddRenderable(render);

        //add states here
    }

    @Override
    public void Update(double dt) {
        sm.Update(dt);

        if(health <= 0)
            SetIsDead(true);
    }


}
