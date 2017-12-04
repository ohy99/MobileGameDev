package com.mgd.mgd.Enemy;

import android.util.Log;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.Vector3;

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
        LISTERIA,
        MINIBOTULISM
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
            enemy.Destroy();
            EnemyList.remove(enemy);
        }
        RemovalList.clear();

        Log.i("Enemy", String.valueOf(EnemyList.size()));
    }


    public void SpawnEnemy(EnemyType type, Vector3 spawnPos) {
        switch(type)
        {
            case BOTULISM:
                Botulism b = new Botulism();
                b.Init();
                EnemyList.add(b);
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
            case MINIBOTULISM:
                MiniBotulism mb = new MiniBotulism(spawnPos);
                mb.Init();
                EnemyList.add(mb);
                break;
        }

    }
}

