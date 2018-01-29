package com.mgd.mgd.Common;

/**
 * Created by HongYu
 */

public class Mtx44 {

    float a[] = new float[16];
    public Mtx44()
    {
        a[0] = 0.f;
        a[1] = 0.f;
        a[2] = 0.f;
        a[3] = 0.f;
        a[4] = 0.f;
        a[5] = 0.f;
        a[6] = 0.f;
        a[7] = 0.f;
        a[8] = 0.f;
        a[9] = 0.f;
        a[10] = 0.f;
        a[11] = 0.f;
        a[12] = 0.f;
        a[13] = 0.f;
        a[14] = 0.f;
        a[15] = 0.f;
    }

    public void SetToRotation(float degrees, float axisX, float axisY, float axisZ) throws Exception
    {
        double mag = Math.sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ);
        //                          EPSILON
        if(Math.abs((float)mag) < 0.00001f)
        throw new Exception();
        double x = axisX / mag, y = axisY / mag, z = axisZ/ mag;
        double c = Math.cos(degrees * Math.PI / 180), s = Math.sin(degrees * Math.PI / 180);
        a[0] = (float)(x * x * (1.f - c) + c);
        a[1] = (float)(y * x * (1.f - c) + z * s);
        a[2] = (float)(x * z * (1.f - c) - y * s);
        a[3] = 0;
        a[4] = (float)(x * y * (1.f - c) - z * s);
        a[5] = (float)(y * y * (1.f - c) + c);
        a[6] = (float)(y * z * (1.f - c) + x * s);
        a[7] = 0;
        a[8] = (float)(x * z * (1.f - c) + y * s);
        a[9] = (float)(y * z * (1.f - c) - x * s);
        a[10] = (float)(z * z * (1.f - c) + c);
        a[11] = 0;
        a[12] = 0;
        a[13] = 0;
        a[14] = 0;
        a[15] = 1;
    }


    public Vector3 Multiply(Vector3 rhs)
    {
        float b[] = new float[4];
        for(int i = 0; i < 4; i++)
            b[i] = a[0 * 4 + i] * rhs.x + a[1 * 4 + i] * rhs.y + a[2 * 4 + i] * rhs.z + a[3 * 4 + i] * 0;
        Vector3 ret = new Vector3(b[0], b[1], b[2]);
        return ret;
    }


}
