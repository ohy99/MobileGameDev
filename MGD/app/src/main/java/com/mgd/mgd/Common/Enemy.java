package com.mgd.mgd.Common;

import com.mgd.mgd.Components.StateManager;
import com.mgd.mgd.Components.Transform;

/**
 * Created by MarcusTan on 3/12/2017.
 */

public class Enemy extends GameObject {
    public StateManager sm = new StateManager();

    protected boolean isDead = false;
    //protected int health = 100;
    protected int attack = 0;
    protected int movespeed = 0;
    protected float attackspeed = 0.f;
    protected float attackRange = 0.f;
    protected float detectRange = 0.f;
    protected String name = "";
    protected float transitionOffset = 0.f;

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

    //public void ReduceHealth(int hp) {
    //    health -= hp;
   // }

    public int GetMoveSpeed() { return movespeed;}
    public int GetAttack() {return attack;}
    public float GetAttackSpeed() {return attackspeed;}
    public float GetAttackRange() { return attackRange;}
    public float GetDetectRange() {return detectRange;}
    public String GetName() {return name;}

    public float GetTransitionOffset() {return transitionOffset;}
}
