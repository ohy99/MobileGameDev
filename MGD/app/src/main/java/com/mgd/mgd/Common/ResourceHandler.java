package com.mgd.mgd.Common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;

import com.mgd.mgd.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ResourceHandler{
    final static public ResourceHandler Instance = new ResourceHandler();

    SurfaceView view;
    class resourceId{
        public resourceId(int res) {this.res = res;}
        public int res = 0;
    }
    Map<resourceId, Bitmap> bitmapMap = new HashMap<>();

    public void Init(SurfaceView view){
        this.view = view;
    }

    public Bitmap GetBitmap (final int res) {
        for (Map.Entry<resourceId, Bitmap> entry : bitmapMap.entrySet())
        {
            if (entry.getKey().res == res)
                return entry.getValue();
        }

        Bitmap bmp =  BitmapFactory.decodeResource(view.getResources(), res);
        bitmapMap.put(new resourceId(res), bmp);
        return bmp;
    }


}
