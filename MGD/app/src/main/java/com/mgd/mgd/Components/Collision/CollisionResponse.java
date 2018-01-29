package com.mgd.mgd.Components.Collision;

import com.mgd.mgd.Common.GameObject;
import com.mgd.mgd.Components.ComponentBase;

/**
 * Created by HongYu
 */
public abstract class CollisionResponse implements ComponentBase {

    public boolean isHit = false;

    @Override
    public void Init(){

    }
    @Override
    public void Update(double dt){

    }

    public boolean Response(GameObject other, GameObject me){
        return false;
    }
}

