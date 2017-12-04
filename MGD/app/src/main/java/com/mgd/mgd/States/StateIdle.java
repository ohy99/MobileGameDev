package com.mgd.mgd.States;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Common.State;
import com.mgd.mgd.Components.Transform;


/**
 * Created by 161832Q on 3/12/2017.
 */

public class StateIdle extends State{
    Enemy enemy;
    public StateIdle(String _StateID, Enemy e) {
        super(_StateID);
        enemy = e;
    }

    @Override
    public void Enter() {
    }

    @Override
    public void Update(double dt) {
        Transform playerTransform = (Transform) Player.Instance.GetComponent("transform");
        Transform transform = (Transform) enemy.GetComponent("transform");

        float distSq = (transform.GetPosition().Subtract(playerTransform.GetPosition().Negate())).LengthSquared();

        if (enemy.GetName().equals("botulism")) {   //botulism detectrange is used for summon to movement, so there is a need for an offset from idle to summon
            if(distSq < (enemy.GetDetectRange() + 20.f) * (enemy.GetDetectRange() + 20.f))
                enemy.sm.SetNextState("summon");
        } else {
            if (distSq < enemy.GetDetectRange() * enemy.GetDetectRange())
                enemy.sm.SetNextState("moving");
        }


    }
}
