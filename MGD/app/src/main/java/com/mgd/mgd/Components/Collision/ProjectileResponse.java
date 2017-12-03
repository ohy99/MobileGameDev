package com.mgd.mgd.Components.Collision;

import com.mgd.mgd.Common.GameObject;
import com.mgd.mgd.Common.GameObjectManager;
import com.mgd.mgd.Common.Player;

public class ProjectileResponse extends CollisionResponse{


    public boolean Response(GameObject other, GameObject me){

        if (other instanceof Player){
           // GameObjectManager.Instance.RequestDeletion(me);
            me.Destroy();
            return true;
        }

        return false;
    }
}
