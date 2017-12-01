package com.mgd.mgd.Common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;

import com.mgd.mgd.R;

public class ResourceHandler{
    final static public ResourceHandler Instance = new ResourceHandler();

    SurfaceView view;

    public void Init(SurfaceView view){
        this.view = view;
    }

    public Bitmap GetBitmap (final int res) {
        return  BitmapFactory.decodeResource(view.getResources(), res);
    }


}
