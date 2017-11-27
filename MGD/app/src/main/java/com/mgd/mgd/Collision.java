package com.mgd.mgd;

public class Collision{
    public static boolean SphereSphere(float x1, float y1, float radius1, float x2, float y2, float radius2) {
        return ((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1)) <= ((radius1 + radius2) * (radius1 + radius2));
    }


}
