package com.mgd.mgd.Common;

import com.mgd.mgd.Components.StateManager;
import com.mgd.mgd.Components.Transform;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class Enemy extends GameObject {
    public StateManager sm = new StateManager();

    protected boolean isDead = false;
    public int health = 100;
    protected int attack = 0;
    protected int movespeed = 0;

    @Override
    public void Init() {

    }

    @Override
    public void Update(double dt) {

    }

    public void SetIsDead(boolean dead) {
        isDead = dead;
    }

    public boolean GetIsDead() {
        return isDead;
    }

    public void ReduceHealth(int hp) {
        health -= hp;
    }
}
