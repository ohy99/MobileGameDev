package com.mgd.mgd.Common;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class Projectiles extends GameObject {

    public enum ProjectileType {
        BULLET,
        GRENADE // cant think of a name for the Range Enemy Projectile
    }

    float yInterference;
    ProjectileType type;
    public Projectiles(ProjectileType pType) {
        type = pType;
    }


    @Override
    public void Init() {
       switch(type)
       {
           case BULLET:
               yInterference = 0.0f;
               break;
           case GRENADE:
               yInterference = -9.8f;
               break;
       }
    }

    @Override
    public void Update(double dt) {

    }
}
