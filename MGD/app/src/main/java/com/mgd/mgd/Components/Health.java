package com.mgd.mgd.Components;

import android.graphics.PointF;

import com.mgd.mgd.Common.ResourceHandler;
import com.mgd.mgd.Common.Vector3;
import com.mgd.mgd.R;

/**
 * Created by HongYu
 */

public class Health implements ComponentBase
{

    int hp =0 ;
    int maxhp;
    Render renderhp = new Render();
    Vector3 hpbarrelativepos = new Vector3(0,0,0);
    Vector3 ownerpos;
    PointF defaultScale;
    Transform pos;

    @Override
    public void Init() {
        //renderhp.Start(ResourceHandler.Instance.GetBitmap());
        hpbarrelativepos.Set(0,1,0);
        defaultScale = new PointF();
        defaultScale.set(7.5f, 2);

        pos = new Transform();
        pos.Init();
        pos.SetScale(defaultScale.x, defaultScale.y);

        renderhp.Init();
        renderhp.Start(ResourceHandler.Instance.GetBitmap(R.drawable.redhpbar), pos);
        RenderManager.Instance.AddRenderable(renderhp);
    }

    @Override
    public void Update(double dt) {
        Vector3 totalpos = ownerpos.Add(hpbarrelativepos);
        pos.SetPosition(totalpos.x, totalpos.y, totalpos.z);

        pos.SetScale((float )this.hp / (float )this.maxhp * defaultScale.x, defaultScale.y);
    }

    public void InitHp(int max)
    {
        this.hp = this.maxhp = max;
    }

    public void SetRelativePos(Vector3 ownerdepos, PointF displacement)
    {
        ownerpos = ownerdepos;
        hpbarrelativepos.x = displacement.x;
        hpbarrelativepos.y = displacement.y;
    }

    public void Destroy()
    {
        RenderManager.Instance.RemoveRenderable(renderhp);
    }

    public void KenaHit(int dmg)
    {
        this.hp -= dmg;
        if (this.hp < 0)
            this.hp = 0;
        if (this.hp > this.maxhp)
            this.hp = this.maxhp;
    }

    public float GetHpPercentage(){
        return (float) this.hp / (float) this.maxhp;
    }
    public int GetHp(){return this.hp;}
    public int GetMaxHp() {return this.maxhp;}
}

