package com.mgd.mgd.States.BotulismStates;

import com.mgd.mgd.Common.State;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class StateSummon extends State {

    private float etSummon;
    public StateSummon(String _StateID) {
        super(_StateID);
    }

    @Override
    public void Enter() {
        etSummon = 0.0f;
    }

    @Override
    public void Update(double dt) {

    }


}
