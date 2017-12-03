package com.mgd.mgd.Enemy;

import com.mgd.mgd.Common.Enemy;

import java.util.LinkedList;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class EnemyManager {
    public final static EnemyManager Instance = new EnemyManager();
    LinkedList<Enemy> EnemyList = new LinkedList<Enemy>();

    private EnemyManager() {}

    public enum EnemyType
    {
        BOTULISM,
        CLOSTRIDIUM,
        SHIGELLA,
        LISTERIA
    }

    public void Init() {

    }

    public void Update(double dt) {
        LinkedList<Enemy> RemovalList = new LinkedList<Enemy>();

        for(Enemy enemy : EnemyList) {
            enemy.Update(dt);


        }


    }


    public void SpawnEnemy(EnemyType type) {
        switch(type)
        {
            case BOTULISM:
                break;
            case CLOSTRIDIUM:
                break;
            case SHIGELLA:
                break;
            case LISTERIA:
                break;
        }

    }

    //public void RemoveEnemy() {

    //}
}

