package com.mgd.mgd.Enemy;

import com.mgd.mgd.Common.Enemy;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class Listeria extends Enemy {

    public Listeria() {};

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
