package com.mgd.mgd.Common;

import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;

public class GenericRenderable extends GameObject{

    @Override
    public void Init(){

        Transform transform = new Transform();
        transform.Init();
        transform.SetScale(200, 100);
        this.components.put("transform", transform);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.background), transform);
        this.components.put("render", render);

        RenderManager.Instance.AddRenderable(render);

    }
}
