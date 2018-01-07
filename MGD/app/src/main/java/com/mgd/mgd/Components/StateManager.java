package com.mgd.mgd.Components;

import android.support.v4.view.NestedScrollingChild;

import com.mgd.mgd.Common.State;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class StateManager {
    Map<String, State> StateMap = new HashMap<>();
    State CurrState = null;
    State NextState = null;


    public StateManager() {}

    public void AddState(State NewState) {
            StateMap.put(NewState.GetStateID(), NewState);
    }

    public void SetNextState(String nextStateID) {
        for(Map.Entry<String,State> i : StateMap.entrySet()) {
            if(nextStateID.equals(i.getKey()))
                NextState = i.getValue();

        }

        if (CurrState == null)
            CurrState = NextState;
    }

    public final String GetCurrentState() {
        if(CurrState!=null)
            return CurrState.GetStateID();
        else
            return "<No States>";
    }

    public void Update(double dt) {
        if (CurrState == null)
            return;
        CurrState.Update(dt);
        if(NextState != CurrState) {
            CurrState.Exit();
            CurrState = NextState;
            NextState.Enter();
        }
    }
}
