package com.mgd.mgd.Components;

import com.mgd.mgd.Common.ResourceHandler;

public class Health implements ComponentBase
{

    int hp =0 ;
    int maxhp;
    Render renderhp = new Render();
    Transform hpbarpos = new Transform();

    @Override
    public void Init() {
        //renderhp.Start(ResourceHandler.Instance.GetBitmap());
    }

    @Override
    public void Update(double dt) {

    }
}
