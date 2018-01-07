package com.mgd.mgd.States;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Common.State;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.Enemy.EnemyManager;

/**
 * Created by 161832Q on 7/1/2018.
 */

public class StateSummon extends State{
    Enemy enemy;
    double etSummon;
    public StateSummon(String _StateID, Enemy e) {
        super(_StateID);
        enemy = e;
    }

    @Override
    public void Enter() {
        etSummon = 0.0;
    }

    @Override
    public void Update(double dt) {
        Transform playerTransform = (Transform) Player.Instance.GetComponent("transform");
        Transform thisTransform = (Transform) enemy.GetComponent("transform");

        float distSquared = (thisTransform.GetPosition().Add(playerTransform.GetPosition().Negate())).LengthSquared();

        // transition
        if(distSquared > (enemy.GetDetectRange() + enemy.GetTransitionOffset()) * (enemy.GetDetectRange() + enemy.GetTransitionOffset()) )
            enemy.sm.SetNextState("randommove");

        etSummon += dt;
        if(etSummon > 2) {
            EnemyManager.Instance.SpawnEnemy(EnemyManager.EnemyType.SHIGELLA, thisTransform.GetPosition());
            etSummon = 0.0;
        }
    }

    @Override
    public void Exit() {

    }
}
