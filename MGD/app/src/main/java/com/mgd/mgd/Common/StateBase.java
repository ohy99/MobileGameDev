package com.mgd.mgd.Common;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class StateBase {
    String StateID;

    public String GetStateID() {
        return StateID;
    }

    StateBase(final String _StateID) {
        StateID = _StateID;
    }

    public void Enter() {

    }

    public void Update(double dt) {

    }

    public void Exit() {

    }
}
