package com.mgd.mgd.Enemy;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Components.StateManager;

public class Clostridium extends Enemy {

        public Clostridium() {}
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
