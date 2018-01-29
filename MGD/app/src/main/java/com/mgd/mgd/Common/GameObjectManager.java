package com.mgd.mgd.Common;


import android.graphics.PointF;
import android.util.Log;

import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.Transform;

import java.util.Vector;

/**
 * Created by HongYu
 */

public class GameObjectManager{
    public final static GameObjectManager Instance = new GameObjectManager();
    Vector<GameObject> gameObjects = new Vector<>();
    Vector<GameObject> removalList = new Vector<>();
    Vector<GameObject> additionList = new Vector<>();

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

            PointF canvasSize = new PointF(RenderManager.Instance.getCanvasWidth(), RenderManager.Instance.getCanvasHeight());
            float ratio = canvasSize.x / canvasSize.y;
            float worldwidth = ratio * 100.f;
            Transform transform = (Transform) go.GetComponent("transform");
            if(transform.GetPosition().x > worldwidth || transform.GetPosition().x < 0
                    || transform.GetPosition().y < 0 || transform.GetPosition().y > 100.f){
                go.Destroy();
            }
        }

        for (GameObject go: additionList
             ) {
            gameObjects.add(go);
        }
        additionList.clear();

        Log.i("GOManager", String.valueOf(gameObjects.size()));
    }


    public void RequestDeletion(GameObject go)
    {
        removalList.add(go);
    }
    public void AddGo(GameObject go) {additionList.add(go);}
    public void RemoveAll(){removalList.clear(); additionList.clear(); gameObjects.clear();}
}
