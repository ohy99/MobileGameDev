package com.mgd.mgd.Enemy;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Components.StateManager;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class Shigella extends Enemy {

    public Shigella() {}
    StateManager sm = new StateManager();

    public void Init() {
        // add states here
        // add variables (hp, atk, movespeed here)
    }

    public void Update(double dt) {
        sm.Update(dt);
    }


    // ....
}
