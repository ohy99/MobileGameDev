package com.mgd.mgd.States;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Common.State;
import com.mgd.mgd.Components.Transform;

import java.util.Random;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class StateAttack extends State{
    Enemy enemy;
    double etAttack;
    Random r = new Random();
    public StateAttack(String _StateID, Enemy e) {
        super(_StateID);
        enemy = e;
    }

    @Override
    public void Enter() { etAttack = 0.f;
    }

    @Override
    public void Update(double dt) {
        Transform playerTransform = (Transform) Player.Instance.GetComponent("transform");
        Transform transform = (Transform) enemy.GetComponent("transform");

        float distSq = (transform.GetPosition().Subtract(playerTransform.GetPosition())).LengthSquared();

        if(distSq > enemy.GetAttackRange() + 5.f)
            enemy.sm.SetNextState("moving");

        etAttack += dt;
        if(etAttack > enemy.GetAttackSpeed()) {
            float damageDealt = (r.nextFloat() + 0.5f) * enemy.GetAttack();
            Player.Instance.SetHealth(Player.Instance.GetHealth() - (int) damageDealt);

            etAttack = 0.f;
        }

    }
}
