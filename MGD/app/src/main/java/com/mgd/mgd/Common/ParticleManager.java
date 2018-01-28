package com.mgd.mgd.Common;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v4.media.session.ParcelableVolumeInfo;

import com.mgd.mgd.Components.Physics;
import com.mgd.mgd.Components.Render;
import com.mgd.mgd.Components.RenderManager;
import com.mgd.mgd.Components.Transform;
import com.mgd.mgd.R;

import java.util.Random;
import java.util.Vector;

public class ParticleManager {
    public static final ParticleManager Instance = new ParticleManager();

    class Particle
    {
        GameObject obj;
        float elapsedTime = 0.0f;
        float lifetime;
    }
    Vector<Particle> particles = new Vector<>();
    Random random = new Random();

    public enum PARTICLETYPE
    {
        PROJECTILE_TRAIL,
    }

    public void Init()
    {

    }

    public void Update(double dt)
    {
        Vector<Particle> removalList = new Vector<>();
        for (Particle p : particles) {
            p.elapsedTime += dt;
            if (p.elapsedTime >= p.lifetime)
            {
                removalList.add(p);
            }
        }
        particles.removeAll(removalList);
        for (Particle removeP : removalList) {
            GameObjectManager.Instance.RequestDeletion(removeP.obj);
            RenderManager.Instance.RemoveRenderable((Render)removeP.obj.GetComponent("render"));
        }
    }

    public void SpawnParticle(PARTICLETYPE type, Vector3 pos, PointF dir)
    {
        switch(type)
        {
            case PROJECTILE_TRAIL:
                PointF dirNegate = new PointF();
                dirNegate.set(dir.x, dir.y);
                dirNegate.negate();
                float spd = random.nextFloat() * 5.f + 10.f;
                float lifeTime = random.nextFloat() * 0.5f + 1.0f;
                SpawnParticle(pos, dirNegate, new PointF(3, 1.5f), 30, spd, lifeTime);
                break;
        }
    }


    private void SpawnParticle(Vector3 pos , PointF dir, PointF scale, float dirRand, float speed, float lifeTime)
    {
        GameObject particle = new GameObject();
        Transform transform = new Transform();
        transform.Init();
        particle.components.put("transform", transform);

        Render render = new Render();
        render.Init();
        render.Start(ResourceHandler.Instance.GetBitmap(R.drawable.redhpbar), transform);
        particle.components.put("render", render);
        RenderManager.Instance.AddRenderable(render);

        transform.SetPosition(pos.x, pos.y, 3);
        transform.SetScale(scale.x, scale.y);

        //dirrand in degree

        Mtx44 rotation = new Mtx44();
        try{
            rotation.SetToRotation(random.nextFloat() * dirRand * 2.f, 0, 0, 1);
        }catch (Exception e)
        {

        }

        Vector3 dir3 = new Vector3(dir.x, dir.y, 0);
        Mtx44 rotation2 = new Mtx44();
        try{
            rotation2.SetToRotation(-dirRand, 0,0,1);
        }catch (Exception e)
        {

        }
        dir3 = rotation2.Multiply(dir3);
        dir3 = rotation.Multiply(dir3);

        transform.SetDir(dir3.x, dir3.y);

        Physics physics = new Physics();
        physics.transform = transform;
        particle.components.put("physics", physics);
        physics.speed = speed;

        GameObjectManager.Instance.AddGo(particle);

        Particle particleClass = new Particle();
        particleClass.obj = particle;
        particleClass.lifetime = lifeTime;

        particles.add(particleClass);

    }

    public void RemoveAll() {particles.clear();}
}

