package com.mgd.mgd.Enemy;

import android.util.Log;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.Vector3;

import java.util.LinkedList;

/**
 * Created by MarcusTan on 3/12/2017.
 */

public class EnemyManager {
    public final static EnemyManager Instance = new EnemyManager();
    LinkedList<Enemy> EnemyList = new LinkedList<Enemy>();
    LinkedList<Enemy> AdditionList = new LinkedList<>();

    private EnemyManager() {}

    float etSpawn = 0.0f;
    float spawnRate = 2.0f;
    public enum EnemyType
    {
        BOTULISM,
        CLOSTRIDIUM,
        SHIGELLA,
        LISTERIA
    }

    public void Init() {
        for(int i = 0; i < 2; ++i) {
           SpawnEnemy(EnemyType.BOTULISM, new Vector3(0,0,0));
           // SpawnEnemy(EnemyType.SHIGELLA, new Vector3(0,0,0));
        }
    }

    public void Update(double dt) {
        etSpawn += dt;
        if(etSpawn > spawnRate) {
            SpawnEnemy(EnemyType.BOTULISM,new Vector3(0,0,0));
            etSpawn = 0.0f;
        }

        LinkedList<Enemy> RemovalList = new LinkedList<Enemy>();

        EnemyList.addAll(AdditionList);
        AdditionList.clear();

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
                AdditionList.add(b);
                break;
            case CLOSTRIDIUM:
                //Clostridium c = new Clostridium();
                break;
            case SHIGELLA:
                Shigella s = new Shigella(spawnPos);
                s.Init();
                AdditionList.add(s);
                break;
            case LISTERIA:
               // Listeria l = new Listeria();
                break;
        }

    }

    public void RemoveAll() { EnemyList.clear(); AdditionList.clear(); }
}

