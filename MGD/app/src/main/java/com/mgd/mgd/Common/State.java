package com.mgd.mgd.Common;

/**
 * Created by MarcusTan on 3/12/2017.
 */

public class State {
    String StateID;

    public String GetStateID() {
        return StateID;
    }

    protected State(final String _StateID) {
        StateID = _StateID;
    }

    public void Enter() {

    }

    public void Update(double dt) {

    }

    public void Exit() {

    }
}
