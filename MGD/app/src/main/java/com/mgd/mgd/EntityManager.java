package com.mgd.mgd;

import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;


public class EntityManager {
    public final static EntityManager Instance = new EntityManager();
    private SurfaceView view = null;
    private LinkedList<EntityBase> entityList = new LinkedList<EntityBase>();

    private EntityManager() {}

    public void Init(SurfaceView _view) {
        view = _view;
    }

    public void Update(float _dt) {
        LinkedList<EntityBase> removalList = new LinkedList<EntityBase>();

        for(EntityBase currEntity : entityList) {
            currEntity.Update(_dt);

            if(currEntity.IsDone()) {
                //Add to removal list for removal
                removalList.add(currEntity);
            }
        }

        // Remove everything in removal list
        for(EntityBase currEntity:removalList)
            entityList.remove(currEntity);

        removalList.clear(); // keep things tidy

        //resolve some collision
        for(int i=0;i<entityList.size();++i) {
            EntityBase currEntity = entityList.get(i);

            if(currEntity instanceof Collidable) {
                Collidable first = (Collidable) currEntity;

                for(int j=i+1;j<entityList.size();++j){
                    EntityBase otherEntity = entityList.get(j);

                    if(otherEntity instanceof Collidable) {
                        Collidable second = (Collidable)otherEntity;

                        if(Collision.SphereSphere(first.GetPosX(),first.GetPosY(),first.GetRadius(),
                                second.GetPosX(),second.GetPosY(),second.GetRadius()))
                        {
                            first.OnHit(second);
                            second.OnHit(first);
                        }
                    }


                }

                if(currEntity.IsDone()) {
                    //Add to removal list for removal
                    removalList.add(currEntity);
                }
            }
        }
        // Remove everything in removal list
        for(EntityBase currEntity:removalList)
            entityList.remove(currEntity);

        removalList.clear(); // keep things tidy

    }

    public void Render(Canvas _canvas){
        try{
            for (EntityBase currEntity : entityList) {
                currEntity.Render(_canvas);
            }
        }catch (ConcurrentModificationException e)
        {

        }

    }

    public void AddEntity(EntityBase _newEntity) {
        _newEntity.Init(view);
        entityList.add(_newEntity);
    }

    public void RemoveAllEntity(){
        entityList.clear();
    }
}
