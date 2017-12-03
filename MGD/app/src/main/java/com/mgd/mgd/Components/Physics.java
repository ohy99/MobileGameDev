package com.mgd.mgd.Components;

import com.mgd.mgd.Common.Vector3;

public class Physics implements ComponentBase{

    boolean gravity = false;
    public Transform transform;
    float speed = 10.0f;

    @Override
    public void Init() {

    }

    @Override
    public void Update(double dt) {
        Vector3 pos;
        pos = transform.GetPosition();
        if (gravity)
        {
            pos.y -= 10.f * dt;
        }
        pos.x += transform.GetDir().x * speed * dt;
        pos.y += transform.GetDir().y * speed * dt;
    }
}
