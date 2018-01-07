package com.mgd.mgd.Components;

import android.graphics.Point;
import android.graphics.PointF;

import com.mgd.mgd.Common.Vector3;

public class Transform implements ComponentBase
{
    private Vector3 position = new Vector3(0,0,0);
    private PointF dir = new PointF();
    private PointF scale = new PointF();

    @Override
    public void Init() {
        position.Set(0,0,0);
        dir.set(1,0);
        scale.set(1,1);
    }

    @Override
    public void Update(double dt) {

    }

    public Vector3 GetPosition() {
        return position;
    }

    public PointF GetDir(){
        return dir;
    }

    public PointF GetScale(){
        return scale;
    }

    public void SetScale(float x, float y){scale.set(x, y);}
    public void SetDir(float x, float y) {dir.set(x,y);}
    public void SetPosition(float x, float y, float z) {position.Set(x,y,z);}
    public void SetPosition2(Vector3 pos) {position = pos;}
}

