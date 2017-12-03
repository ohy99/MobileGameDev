package com.mgd.mgd.Components.Collision;

import com.mgd.mgd.Common.Vector3;
import com.mgd.mgd.Components.ComponentBase;


public class Collider implements ComponentBase {

    //use aabb for now
    public Vector3 min, max;
    public CollisionResponse response;

    @Override
    public void Init() {
        min = new Vector3(0,0,0);
        max = new Vector3(0,0,0);
    }

    @Override
    public void Update(double dt) {

    }
}

