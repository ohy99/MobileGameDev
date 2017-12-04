package com.mgd.mgd.States;

import android.util.Log;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.Common.State;
import com.mgd.mgd.Common.Vector3;
import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class StateMoving extends State {
    Enemy e;
    public StateMoving(String _StateID, Enemy enemy) {
        super(_StateID);
        e = enemy;
    }

    @Override
    public void Enter() {
        Render r = (Render) e.GetComponent("render");
        r.Start(ResourceHandler.Instance.GetBitmap(R.drawable.listeria),(Transform)e.GetComponent("transform"));

        Log.i("STATE","CHANGED");
    }

    @Override
    public void Update(double dt) {
        Transform playerTransform = (Transform) Player.Instance.GetComponent("transform");
        Transform transform = (Transform) e.GetComponent("transform");

        Vector3 moveDir = ((transform.GetPosition().Negate()).Add(playerTransform.GetPosition()));

        Vector3 tempPos = transform.GetPosition().Add(moveDir.Multiply(e.GetMoveSpeed() * (float)dt)) ;
        transform.SetPosition(tempPos.x,tempPos.y,tempPos.z);

        float distSq = (transform.GetPosition().Subtract(playerTransform.GetPosition().Negate())).LengthSquared();

        if(distSq < e.GetAttackRange() * e.GetAttackRange()) {
            e.sm.SetNextState("attack");
        }
         else if(distSq > (e.GetDetectRange() + 5.f) * (e.GetDetectRange() + 5.f)) {
                if(e.GetName().equals("botulism")) {
                    e.sm.SetNextState("summon");
                } else {
                    e.sm.SetNextState("idle");
                }
            }

    }
}
