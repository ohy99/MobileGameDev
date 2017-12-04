package com.mgd.mgd.Components.Collision;

import com.mgd.mgd.Common.GameObject;

public class PlayerResponse extends CharacterResponse{

    //increase score here
    @Override
    public boolean Response(GameObject other, GameObject me){
        return false;
    }
}

