package com.mgd.mgd.Components;

import com.mgd.mgd.Common.Vector3;

public class Physics implements ComponentBase{

    boolean gravity = false;
    Transform transform;
    float speed = 1.0f;

    @Override
    public void Init() {

    }

    @Override
    public void Update(double dt) {
        if (gravity)
        {
            Vector3 pos;
            pos = transform.GetPosition();
            pos.y -= 10.f * dt;
            pos.x += transform.GetDir().x * speed;
            pos.y += transform.GetDir().y * speed;
        }
    }
}
