package com.mgd.mgd.Components.Collision;

import com.mgd.mgd.Common.Vector3;
import com.mgd.mgd.Components.ComponentBase;
import com.mgd.mgd.Components.Transform;


public class Collider implements ComponentBase {

    //use aabb for now
    public Vector3 min, max;
    public CollisionResponse response;
    public Transform transform;

    @Override
    public void Init() {
        min = new Vector3(0,0,0);
        max = new Vector3(0,0,0);
    }

    @Override
    public void Update(double dt) {

    }
}

