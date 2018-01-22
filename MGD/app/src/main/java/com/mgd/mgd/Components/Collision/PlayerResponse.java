package com.mgd.mgd.Components.Collision;

import com.mgd.mgd.Common.Enemy;
import com.mgd.mgd.Common.GameObject;
import com.mgd.mgd.Common.Projectiles;
import com.mgd.mgd.Components.Health;

public class PlayerResponse extends CharacterResponse{

    Health ownerHp;

    public void Init(Health hp)
    {
        ownerHp = hp;
    }
    //increase score here
    @Override
    public boolean Response(GameObject other, GameObject me){
        if (other instanceof  Projectiles)
        {
            if (me instanceof Enemy)
            {
                //only i can hit enemy yay
                Projectiles proj = (Projectiles) other;
                ownerHp.KenaHit( proj.GetDamageToHit());
            }

        }

        return false;
    }
}

