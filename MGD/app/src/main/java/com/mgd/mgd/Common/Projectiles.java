package com.mgd.mgd.Common;

import android.graphics.PointF;
import android.util.Log;

import com.mgd.mgd.Components.Collision.Collider;
import com.mgd.mgd.Components.Collision.CollisionManager;
import com.mgd.mgd.Components.Collision.ProjectileResponse;
import com.mgd.mgd.Components.ComponentBase;
import com.mgd.mgd.Components.Physics;
import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;
import com.mgd.mgd.SampleGame;

import java.util.Map;
import java.util.Random;

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

    double elapsed = 0.0;
    double spawnDelay= 0.1;
    Random random = new Random();

    @Override
    public void Init() {
//       switch(type)
//       {
//           case BULLET:
//               yInterference = 0.0f;
//               break;
//           case GRENADE:
//               yInterference = -9.8f;
//               break;
//       }


       //construct components
        Transform transform = new Transform();
       transform.Init();
       this.components.put("transform", transform);

        Collider collider = new Collider();
        collider.Init();

        ProjectileResponse response = new ProjectileResponse();
        response.Init();
        collider.response = response;
        collider.transform = transform;
        this.components.put("collider", collider);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.play), transform);
        this.components.put("render", render);
        RenderManager.Instance.AddRenderable(render);

        Physics physics = new Physics();
        physics.Init();
        physics.transform = transform;
        this.components.put("physics", physics);

        GameObjectManager.Instance.AddGo(this);
        CollisionManager.instance.addCollider(collider, this);

        Log.i("Proj", "Created");
    }

    @Override
    public void Update(double dt) {

            for (Map.Entry<String, ComponentBase> entry : components.entrySet()) {

                //physics component will update its movement
                entry.getValue().Update(dt);

            }

        elapsed += dt;
        if (elapsed > spawnDelay)
        {
            Random random = new Random();

            int randValue = random.nextInt(3);
            for (int i = 0; i < randValue; ++i)
            {
                Transform transform = (Transform) GetComponent("transform");
                ParticleManager.Instance.SpawnParticle(ParticleManager.PARTICLETYPE.PROJECTILE_TRAIL, transform.GetPosition(), transform.GetDir());
            }

            elapsed = 0.0;
            spawnDelay = ParticleManager.Instance.random.nextDouble() * 0.3 + 0.2;
        }

    }

    @Override
    public void Destroy(){
        GameObjectManager.Instance.RequestDeletion(this);
        CollisionManager.instance.removeCollider((Collider) this.components.get("collider"), this);
        RenderManager.Instance.RemoveRenderable((Render) this.components.get("render"));

        Log.i("Proj", "Destroyed");
    }

    public void Set(Vector3 pos, PointF dir, PointF scale){
        Transform transform = (Transform) this.components.get("transform");
        transform.SetDir(dir.x, dir.y);
        transform.SetPosition(pos.x, pos.y,pos.z);
        transform.SetScale(scale.x, scale.y);
    }
}
