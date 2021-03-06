package com.mgd.mgd.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.Log;
import android.graphics.Paint;

import com.mgd.mgd.Common.Constants;
import com.mgd.mgd.Common.Player;

import java.util.Vector;

/**
 * Created by HongYu
 */
public class RenderManager{
    public final static RenderManager Instance = new RenderManager();
    Vector<Render> renderables = new Vector<>();

    private float canvasWidth = 0;
    private float canvasHeight = 0;

    public void Render(Canvas canvas){

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
//        float worldHeight = 100.f;
//        float worldWidth = worldHeight * (canvasWidth / canvasHeight);
        float worldHeight = Constants.worldHeight;
        float worldWidth = Constants.worldWidth;

        //sort render order by z-axis
        quick_sort(renderables, 0, renderables.size() - 1);

        for (Render render: renderables) {
            //write here . here determines where the image is rendered
            Bitmap bmp = render.GetBmp();
            Transform transform = render.transform;
            Matrix mtx= new Matrix();

            //have a function to get the x and y base on z also



            //our x is the longer length of the phone


            int bmpWidth = bmp.getWidth();
            int bmpHeight = bmp.getHeight();
            //mtx.setRotate((float)Math.atan2(transform.GetDir().y, transform.GetDir().x));
            //mtx.setRotate(90);
            float rad = (float)Math.atan2(transform.GetDir().y, transform.GetDir().x);
            float deg = (float)Math.atan2(transform.GetDir().y, transform.GetDir().x) / (float)Math.PI * 180.f;
            mtx.setRotate(-(float)Math.atan2(transform.GetDir().y, transform.GetDir().x) / (float)Math.PI * 180.f);
            //mtx.postTranslate(-bmpWidth * 0.5f, -bmpHeight * 0.5f);
            //mtx.postRotate(-(float)Math.atan2(transform.GetDir().y, transform.GetDir().x) / (float)Math.PI * 180.f, bmpWidth * 0.5f, bmpHeight * 0.5f);
            //mtx.setRotate(-(float)Math.atan2(transform.GetDir().y, transform.GetDir().x) / (float)Math.PI * 180.f, bmpWidth * 0.5f, bmpHeight * 0.5f);
            mtx.postTranslate(-bmpWidth * 0.5f, -bmpHeight * 0.5f);

            mtx.postScale((transform.GetScale().x / worldWidth) * (canvasWidth/(float)bmpWidth),
                     (transform.GetScale().y / worldHeight) * (canvasHeight/(float)bmpHeight));

            mtx.postTranslate((transform.GetPosition().x / worldWidth) * (canvasWidth),
                    //transform.GetPosition().y);
                    (1.f - (transform.GetPosition().y / worldHeight)) * (canvasHeight) -
                            (transform.GetScale().y / worldHeight) * (canvasHeight/(float)bmpHeight));
            canvas.drawBitmap(bmp, mtx ,null);

        }

        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(60);
        ScoreSystem ss = (ScoreSystem) Player.Instance.GetComponent("score");

        canvas.drawText("Score:" + String.valueOf(ss.GetScore()), 1350, 70, paint);
        canvas.drawText("Combo:" + String.valueOf(ss.GetCombo()),1350,120, paint);
        Health hpComponent = (Health)Player.Instance.GetComponent("hp");
        canvas.drawText("HP:" + String.valueOf(hpComponent.GetHp()),800,70, paint);

    }

    public void AddRenderable(Render render) {renderables.add(render);}
    public void RemoveRenderable(Render render) {renderables.remove(render);}
    public void RemoveAll() {renderables.clear();}


    private void quick_sort( Vector<Render> data_list, int first_index, int last_index){
        if (first_index < last_index)
        {
            int pivot_location = partition(data_list, first_index, last_index);
            quick_sort(data_list, first_index, pivot_location - 1);
            quick_sort(data_list, pivot_location + 1, last_index);
        }
    }
    private int partition( Vector<Render> data_list , int first_index, int last_index){
        int middle_index = (first_index + last_index) / 2;
        Render pivot_value = data_list.get(middle_index);
        int small_index = first_index;


        swap(data_list, first_index, middle_index);
        for (int check_index = (first_index + 1); check_index <= last_index; check_index++)
        {
            if (data_list.get(check_index).transform.GetPosition().z < pivot_value.transform.GetPosition().z)
            {
                small_index++;
                swap(data_list, small_index, check_index);
            }
        }
        swap(data_list, first_index, small_index);

        return small_index;
    }

    private void swap(Vector<Render> data_list, int first_index, int second_index)
    {
        Render temp = data_list.get(first_index);
        data_list.setElementAt(data_list.get(second_index), first_index);
        data_list.setElementAt(temp, second_index);
//        data_list.get(first_index) = data_list.get(second_index);
//        data_list.get(second_index) = temp;
    }

    public float getCanvasHeight() {
        return canvasHeight;
    }

    public float getCanvasWidth() {
        return canvasWidth;
    }
}
