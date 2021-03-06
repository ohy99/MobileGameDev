package com.mgd.mgd.Common;

import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;

/**
 * Created by HongYu
 */
public class GenericRenderable extends GameObject{

    @Override
    public void Init(){

        Transform transform = new Transform();
        transform.Init();
        transform.SetScale(Constants.worldWidth, Constants.worldHeight);
        transform.SetPosition(Constants.worldWidth * 0.5f, Constants.worldHeight * 0.5f, 0);
        this.components.put("transform", transform);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.background), transform);
        this.components.put("render", render);

        RenderManager.Instance.AddRenderable(render);

    }
}

