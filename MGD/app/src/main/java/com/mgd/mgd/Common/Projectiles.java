package com.mgd.mgd.Common;

import com.mgd.mgd.Components.Collision.Collider;
import com.mgd.mgd.Components.Collision.ProjectileResponse;
import com.mgd.mgd.Components.ComponentBase;
import com.mgd.mgd.Components.Physics;
import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;

import java.util.Map;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class Projectiles extends GameObject {

    public enum ProjectileType {
        BULLET,
        GRENADE // cant think of a name for the Range Enemy Projectile
    }

    float yInterference;
    ProjectileType type;
    public Projectiles(ProjectileType pType) {
        type = pType;
    }


    @Override
    public void Init() {
       switch(type)
       {
           case BULLET:
               yInterference = 0.0f;
               break;
           case GRENADE:
               yInterference = -9.8f;
               break;
       }


       //construct components
        Transform transform = new Transform();
       transform.Init();
       this.components.put("transform", transform);

        Collider collider = new Collider();
        collider.Init();

        ProjectileResponse response = new ProjectileResponse();
        response.Init();
        collider.response = response;
        this.components.put("collider", response);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.play), transform);
        this.components.put("render", render);

        Physics physics = new Physics();
        physics.Init();
        physics.transform = transform;
        this.components.put("physics", transform);

        GameObjectManager.Instance.AddGo(this);
    }

    @Override
    public void Update(double dt) {
        for (Map.Entry<String, ComponentBase> entry : components.entrySet()) {

            //physics component will update its movement
            entry.getValue().Update(dt);
        }
    }

    @Override
    public void Destroy(){
        GameObjectManager.Instance.RequestDeletion(this);
    }
}
