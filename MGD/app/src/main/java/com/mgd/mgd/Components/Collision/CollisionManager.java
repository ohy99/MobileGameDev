package com.mgd.mgd.Components.Collision;

import android.util.Log;
import android.util.Pair;

import com.mgd.mgd.Collision;
import com.mgd.mgd.Common.GameObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class CollisionManager{
    public final static CollisionManager instance = new CollisionManager();

    Vector<Pair<Collider, GameObject>> colliderVector = new Vector<>();
    
    public void Update(double dt){

        for (int i = 0; i < colliderVector.size(); ++i)
        {
            Collider first = colliderVector.get(i).first;
            for (int j = i + 1; j < colliderVector.size(); ++j)
            {
                Collider second = colliderVector.get(j).first;

                if (Collision.SphereSphere(1,2,3,4,5,6))
                {
                    //i , j then j , i
                    first.response.Response(colliderVector.get(j).second, colliderVector.get(i).second);
                    second.response.Response(colliderVector.get(i).second, colliderVector.get(j).second);
                }
            }
        }

        Log.i("CollisionManager", "Update: collisionSize " + String.valueOf(colliderVector.size()));
    }

    public void addCollider(Collider collider, GameObject go) { this.colliderVector.add(Pair.create(collider, go));}
    public void removeCollider(Collider collider, GameObject go) {this.colliderVector.remove(Pair.create(collider, go));}
}
