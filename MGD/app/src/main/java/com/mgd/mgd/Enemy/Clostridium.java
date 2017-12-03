package com.mgd.mgd.Enemy;

import com.mgd.mgd.Common.Enemy;

public class Clostridium extends Enemy {

        public Clostridium() {}

        @Override
        public void Init() {
                // add states here
                // add variables (hp, atk, movespeed here)
        }

        @Override
        public void Update(double dt) {
                sm.Update(dt);
        }

}
