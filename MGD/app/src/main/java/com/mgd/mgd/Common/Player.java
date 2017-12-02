package com.mgd.mgd.Common;

import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.ScoreSystem;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;

public class Player extends GameObject{

    @Override
    public void Init(){
        Transform transform = new Transform();
        transform.Init();
        transform.SetScale(50,50);
        this.components.add(transform);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.settings), transform);
        this.components.add(render);
        RenderManager.Instance.AddRenderable(render);

        ScoreSystem score = new ScoreSystem();
        score.Init();
        this.components.add(score);
    }

    @Override
    public void Update(double dt) {

    }

}

