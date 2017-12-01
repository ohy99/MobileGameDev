package com.mgd.mgd.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by 162183P on 12/1/2017.
 */

public class Render implements ComponentBase {

    private Bitmap bmp = null;
    Transform transform = null;


    @Override
    public void Init() {

    }

    @Override
    public void Update(double dt) {

    }


    public void Start(Bitmap bmp, Transform transform){
        //assign the texture to me
        this.bmp = bmp;
        this.transform = transform;
    }

    public Bitmap GetBmp(){return bmp;}

//
//    public void Render(Canvas canvas){
//        canvas.drawBitmap(bmp, transform.GetPosition().x - bmp.getWidth() * 0.5f, transform.GetPosition().y - bmp.getHeight() * 0.5f,null);
//    }
}
