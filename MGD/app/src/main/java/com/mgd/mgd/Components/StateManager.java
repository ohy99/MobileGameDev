package com.mgd.mgd.Components;

import com.mgd.mgd.Common.State;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class StateManager {
    Map<String, State> StateMap = new HashMap<>();
    State CurrState;
    State NextState;

    public StateManager() {}

    public void AddState(State NewState) {
        if(NewState == null)
            return;

        for(Map.Entry<String,State> i : StateMap.entrySet()) {
            if(i == NewState)
                return;

            if(CurrState == null)
                CurrState = NextState = NewState;

            StateMap.put(NewState.GetStateID(), NewState);
        }
    }

    public void SetNextState(String nextStateID) {
        for(Map.Entry<String,State> i : StateMap.entrySet()) {
            if(nextStateID.equals(i.getKey()))
                NextState = i.getValue();
        }
    }

    public final String GetCurrentState() {
        if(CurrState!=null)
            return CurrState.GetStateID();
        else
            return "<No States>";
    }

    public void Update(double dt) {
        if(NextState != CurrState) {
            CurrState.Exit();
            CurrState = NextState;
            NextState.Enter();
        }
        NextState.Update(dt);
    }
}
