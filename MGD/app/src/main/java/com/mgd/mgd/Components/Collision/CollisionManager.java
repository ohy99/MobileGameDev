package com.mgd.mgd.Components.Collision;

import android.util.Log;
import android.util.Pair;

import com.mgd.mgd.Collision;
import com.mgd.mgd.Common.GameObject;
import com.mgd.mgd.Common.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class CollisionManager{
    public final static CollisionManager instance = new CollisionManager();

    Vector<Pair<Collider, GameObject>> colliderVector = new Vector<>();
    Vector<Pair<Collider, GameObject>> removalList = new Vector<>();
    
    public void Update(double dt){

        for (int i = 0; i < colliderVector.size(); ++i)
        {
            Collider first = colliderVector.get(i).first;
            for (int j = i + 1; j < colliderVector.size(); ++j)
            {
                Collider second = colliderVector.get(j).first;

                if (Collision.SphereSphere(first.transform.GetPosition().x,first.transform.GetPosition().y,first.transform.GetScale().x * 0.5f,
                        second.transform.GetPosition().x,second.transform.GetPosition().y,second.transform.GetScale().x * 0.5f))
                {
                    //i , j then j , i

                        first.response.Response(colliderVector.get(j).second, colliderVector.get(i).second);
                        second.response.Response(colliderVector.get(i).second, colliderVector.get(j).second);

                     Log.i("CManager", "boom");


                }
            }
        }


        colliderVector.removeAll(removalList);
//        for (Pair<Collider, GameObject> p : removalList
//             ) {
//            colliderVector.remove(p);
//        }
        removalList.clear();

        Log.i("CollisionManager", "Update: collisionSize " + String.valueOf(colliderVector.size()));
    }

    public void addCollider(Collider collider, GameObject go) { this.colliderVector.add(Pair.create(collider, go));}
    public void removeCollider(Collider collider, GameObject go) {
        //this.colliderVector.remove(Pair.create(collider, go));
        removalList.add(Pair.create(collider, go));
        }

    public void RemoveAll () {colliderVector.clear(); removalList.clear(); }
}
