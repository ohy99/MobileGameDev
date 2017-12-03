package com.mgd.mgd.Common;


import java.util.Vector;

public class GameObjectManager{
    public final static GameObjectManager Instance = new GameObjectManager();
    Vector<GameObject> gameObjects = new Vector<>();
    Vector<GameObject> removalList = new Vector<>();

    public void Update(double dt)
    {
        for (GameObject remove: removalList
             ) {
            gameObjects.remove(remove);
        }
        removalList.clear();

        for (GameObject go: gameObjects
             ) {
            go.Update(dt);
        }
    }


    public void RequestDeletion(GameObject go)
    {
        removalList.add(go);
    }
    public void AddGo(GameObject go) {gameObjects.add(go);}
}
