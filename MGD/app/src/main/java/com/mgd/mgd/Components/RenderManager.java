package com.mgd.mgd.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.Vector;

public class RenderManager{
    public final static RenderManager Instance = new RenderManager();
    Vector<Render> renderables = new Vector<>();

    public void Render(Canvas canvas){

        //sort render order by z-axis
        quick_sort(renderables, 0, renderables.size() - 1);

        for (Render render: renderables) {
            //write here . here determines where the image is rendered
            Bitmap bmp = render.GetBmp();
            Transform transform = render.transform;
            Matrix mtx= new Matrix();

            //have a function to get the x and y base on z also
            mtx.setTranslate(transform.GetPosition().x, transform.GetPosition().y);

            //our x is the longer length of the phone
            float worldX = 100.f * (canvas.getWidth() / (float)canvas.getHeight());
            int screenWidth = canvas.getWidth();
            int screenHeight = canvas.getHeight();
            int bmpWidth = bmp.getWidth();
            int bmpHeight = bmp.getHeight();
            mtx.setScale((transform.GetScale().x / worldX) * (bmp.getWidth() / (float)canvas.getWidth()),
            (transform.GetScale().y / 100) * (bmp.getHeight() / (float)canvas.getHeight()));

            canvas.drawBitmap(bmp, mtx ,null);

        }

    }

    public void AddRenderable(Render render) {renderables.add(render);}
    public void RemoveRenderable(Render render) {renderables.remove(render);}


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

}