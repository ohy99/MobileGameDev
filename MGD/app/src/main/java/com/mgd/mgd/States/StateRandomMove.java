package com.mgd.mgd.States;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Common.State;
import com.mgd.mgd.Common.Vector3;
import com.mgd.mgd.Components.Transform;

import java.util.Random;

/**
 * Created by 161832Q on 7/1/2018.
 */

public class StateRandomMove extends State {
    Enemy enemy;
    Random random = new Random();
    Vector3 direction = new Vector3(0,0,0);

    public StateRandomMove(String _StateID, Enemy e) {
        super(_StateID);
        enemy = e;
    }

    @Override
    public void Enter() {

    }

    @Override
    public void Update(double dt) {
        direction.Set(random.nextFloat() - 0.5f, random.nextFloat() - 0.5f,1);

        Transform playerTransform = (Transform) Player.Instance.GetComponent("transform");
        Transform thisTransform = (Transform) enemy.GetComponent("transform");

        Vector3 tempPos = thisTransform.GetPosition().Add(direction.Multiply(enemy.GetMoveSpeed() * (float)(dt)));
        thisTransform.SetPosition(tempPos.x,tempPos.y,tempPos.z);

        Vector3 wad = thisTransform.GetPosition();
        Vector3 wad2 = playerTransform.GetPosition().Negate();
        float distSquared = (thisTransform.GetPosition().Add(playerTransform.GetPosition().Negate())).LengthSquared();


        //enemy.sm.SetNextState("summon");
        // transition
        if(distSquared < (enemy.GetDetectRange() + enemy.GetTransitionOffset()) * (enemy.GetDetectRange() + enemy.GetTransitionOffset()) ) {
            if(enemy.GetName().equals("botulism")) {
                enemy.sm.SetNextState("summon");
            }
            else {
                enemy.sm.SetNextState("move");
            }
        }




    }

    @Override
    public void Exit() {

    }
}
