package com.mgd.mgd.States.BotulismStates;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.Player;
import com.mgd.mgd.Common.State;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.Enemy.EnemyManager;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class StateSummon extends State {

    private float etSummon;
    Enemy e;
    public StateSummon(String _StateID, Enemy enemy) {
        super(_StateID);
        e = enemy;
    }

    @Override
    public void Enter() {
        etSummon = 0.0f;
    }

    @Override
    public void Update(double dt) {
        Transform transform = (Transform) e.GetComponent("transform");
        Transform playerTransform = (Transform) Player.Instance.GetComponent("transform");

        float distSq = (transform.GetPosition().Subtract(playerTransform.GetPosition())).LengthSquared();

        if(distSq < e.GetDetectRange())
            e.sm.SetNextState("moving");

        if(distSq > e.GetDetectRange() + 25.f)
            e.sm.SetNextState("idle");

        // Summon 1 puppet every 4s
        etSummon += dt;
        if(etSummon > 4.f) {
            EnemyManager.Instance.SpawnEnemy(EnemyManager.EnemyType.MINIBOTULISM, transform.GetPosition());
            etSummon = 0.f;
        }
    }


}
