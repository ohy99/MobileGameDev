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

            if(enemy.GetIsDead() == true)
                RemovalList.add(enemy);
        }

        // Removal of dead enemies
        for(Enemy enemy : RemovalList) {
            EnemyList.remove(enemy);
        }
        RemovalList.clear();
    }


    public void SpawnEnemy(EnemyType type) {
        switch(type)
        {
            case BOTULISM:
                Botulism b = new Botulism();
                break;
            case CLOSTRIDIUM:
                Clostridium c = new Clostridium();
                break;
            case SHIGELLA:
                Shigella s = new Shigella();
                break;
            case LISTERIA:
                Listeria l = new Listeria();
                break;
        }

    }
}

