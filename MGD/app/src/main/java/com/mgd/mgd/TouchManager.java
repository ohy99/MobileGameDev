package com.mgd.mgd;

import android.view.MotionEvent;

// this is to create a common class so that it can be use for cross platform
public class TouchManager {
    public final static TouchManager Instance = new TouchManager();

    public enum TouchState
    {
        NONE,
        DOWN,
        MOVE
    }

    private TouchState status = TouchState.NONE;
    private int posX, posY;

    private TouchManager()
    {
        posX = posY = 0;
    }

    public boolean HasTouch() {return status == TouchState.DOWN || status == TouchState.MOVE;}
    public boolean IsDown() {return status == TouchState.DOWN;}
    public int GetPosX() {return posX;}
    public int GetPosY() {return posY;}

    public void Update(int _posX, int _posY, int motionEventStatus) {
        posX = _posX;
        posY = _posY;

        switch(motionEventStatus) {
            case MotionEvent.ACTION_DOWN:
                status = TouchState.DOWN;
                break;

            case MotionEvent.ACTION_MOVE:
                status=TouchState.MOVE;
                break;

            case MotionEvent.ACTION_UP:
                status = TouchState.NONE;
                break;
        }
    }
}

