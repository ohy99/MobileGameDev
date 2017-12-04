package com.mgd.mgd.Components.Collision;

import android.util.Log;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.GameObject;
import com.mgd.mgd.Common.GameObjectManager;
import com.mgd.mgd.Common.Player;

public class ProjectileResponse extends CollisionResponse{

    @Override
    public boolean Response(GameObject other, GameObject me){

        if (other instanceof Player)
            return false;

        if (other instanceof Enemy && !(me instanceof Enemy)){
           // GameObjectManager.Instance.RequestDeletion(me);
            if (!this.isHit)
            {
                me.Destroy();
                Enemy e = (Enemy) other;
                e.SetIsDead(true);
                Log.i("Proj Res", "e.ISDead");
                this.isHit = true;
                return true;
            }

        }

        return false;
    }
}
