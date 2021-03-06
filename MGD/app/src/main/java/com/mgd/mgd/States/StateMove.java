package com.mgd.mgd.States;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Common.State;
import com.mgd.mgd.Common.Vector3;
import com.mgd.mgd.Components.Transform;

/**
 * Created by MarcusTan on 7/1/2018.
 */

public class StateMove extends State {
    Enemy enemy;
    public StateMove(String _StateID, Enemy e) {
        super(_StateID);
        enemy = e;
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Update(double dt) {
        Transform playerTransform = (Transform) Player.Instance.GetComponent("transform");
        Transform thisTransform = (Transform) enemy.GetComponent("transform");

        Vector3 moveDirection = ((thisTransform.GetPosition().Negate()).Add(playerTransform.GetPosition()));
        Vector3 tempPos = thisTransform.GetPosition().Add(moveDirection.Multiply(enemy.GetMoveSpeed() * (float)(dt)));
        //thisTransform.SetPosition(tempPos.x,tempPos.y, 2);

        thisTransform.Move(moveDirection.Multiply(enemy.GetMoveSpeed() * (float)dt));
        thisTransform.SetPosition(thisTransform.GetPosition().x, thisTransform.GetPosition().y, 2);


        float distSquared = (thisTransform.GetPosition().Add(playerTransform.GetPosition().Negate())).LengthSquared();

        if(enemy.GetName().equals("shigella")) {
            if (distSquared < enemy.GetAttackRange() * enemy.GetAttackRange()) {
                enemy.sm.SetNextState("attack");
            } else if (distSquared > (enemy.GetDetectRange() + enemy.GetTransitionOffset()) * (enemy.GetDetectRange() + enemy.GetTransitionOffset())) {
                enemy.sm.SetNextState("randommove");
            }
        }

        if(enemy.GetName().equals("botulism")) {
            if (distSquared < enemy.GetDetectRange() * enemy.GetDetectRange()) {
                enemy.sm.SetNextState("summon");
            }
        }
    }

    @Override
    public void Exit() {

    }
}
