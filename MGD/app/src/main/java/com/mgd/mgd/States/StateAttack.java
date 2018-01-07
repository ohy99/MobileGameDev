package com.mgd.mgd.States;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Common.State;
import com.mgd.mgd.Components.ScoreSystem;
import com.mgd.mgd.Components.Transform;

import java.util.Random;

/**
 * Created by 161832Q on 7/1/2018.
 */

public class StateAttack extends State{

    Enemy enemy;
    double etAttack;
    Random random = new Random();
    ScoreSystem score = (ScoreSystem) Player.Instance.GetComponent("score");

    public StateAttack(String _StateID, Enemy e) {
        super(_StateID);
        enemy = e;
    }

    @Override
    public void Enter() {
        etAttack = 0.0;
    }

    @Override
    public void Update(double dt) {
        Transform playerTransform = (Transform) Player.Instance.GetComponent("transform");
        Transform thisTransform = (Transform) enemy.GetComponent("transform");

        float distSquared = (thisTransform.GetPosition().Add(playerTransform.GetPosition().Negate())).LengthSquared();

        // transition
        if(distSquared > (enemy.GetAttackRange() + enemy.GetTransitionOffset()) * (enemy.GetAttackRange() + enemy.GetTransitionOffset()) )
            enemy.sm.SetNextState("move");

        etAttack += dt;
        if(etAttack > enemy.GetAttackSpeed()) {
            float dmgDealt = (random.nextFloat() + 0.5f) * enemy.GetAttack();
            Player.Instance.SetHealth(Player.Instance.GetHealth() - (int)dmgDealt);
            score.MinusScore((int)dmgDealt);
            score.ResetCombo();

            etAttack = 0.0;
        }

    }

    @Override
    public void Exit() {

    }
}
