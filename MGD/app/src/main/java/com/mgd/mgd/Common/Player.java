package com.mgd.mgd.Common;

import android.graphics.PointF;

import com.mgd.mgd.Components.Collision.Collider;
import com.mgd.mgd.Components.Collision.PlayerResponse;
import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;
import com.mgd.mgd.TouchManager;

public class Player extends GameObject{

    @Override
    public void Init(){
        Transform transform = new Transform();
        transform.Init();
        transform.SetScale(50,50);
        this.components.add(transform);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.settings), transform);
        this.components.add(render);
        RenderManager.Instance.AddRenderable(render);

        //init collision
        PlayerResponse playerResponse = new PlayerResponse();
        playerResponse.Init();

        Collider collider = new Collider();
        collider.Init();
        collider.response = playerResponse;
        this.components.add(collider);
    }

    @Override
    public void Update(double dt) {

        UpdateMovement(dt);
    }

    @Override
    public void Destroy(){

    }


    private void UpdateMovement(double dt){

        if (TouchManager.Instance.IsDown())
        {
            PointF touch = new PointF(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY());

            //if touch screen, move char
        }
    }

}


