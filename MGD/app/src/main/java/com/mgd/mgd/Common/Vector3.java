package com.mgd.mgd.Common;

/**
 * Created by HongYu on 12/1/2017.
 */

public class Vector3 {
    public float x, y, z;

    final float EPSILON = 0.00001f;  ///Used for error checking

    boolean IsEqual(float a, float b)
    {
        return a - b <= EPSILON && b - a <= EPSILON;
    }


    public Vector3(float a, float b, float c)
    {
        this.x = a; this.y = b; this.z = c;
    }

    //Copy Constructor
    public Vector3(final Vector3 rhs)
    {
        this.x = rhs.x; this.y = rhs.y; this.z = rhs.z;
    }

    //~Vector3();

    public void Set( float a, float b, float c) //Set all data
    {
        this.x = a; this.y = b; this.z = c;
    }

    public void SetZero() //Set all data to zero
    {
        this.x = this.y = this.z = 0;
    }

    public boolean IsZero() //Check if data is zero
    {
        return IsEqual(x, 0.f) && IsEqual(y, 0.f) && IsEqual(z, 0.f);
    }

    public Vector3 Add(Vector3 rhs) //Vector addition
    {
        Vector3 ret = new Vector3(x + rhs.x, y + rhs.y, z + rhs.z);
        //return Vector3(x + rhs.x, y + rhs.y, z + rhs.z);
        return ret;
    }
    public Vector3 AddEqual(Vector3 rhs)
    {
        x += rhs.x;
        y += rhs.y;
        z += rhs.z;
        return this;
    }

    public Vector3 Subtract( Vector3 rhs ) //Vector subtraction
    {
        Vector3 ret = new Vector3(x - rhs.x, y - rhs.y, z - rhs.z);
        return ret;
    }
    public Vector3 SubtractEqual( Vector3 rhs )
    {
        x -= rhs.x;
        y -= rhs.y;
        z -= rhs.z;
        return this;
    }

    public Vector3 Negate() //Unary negation
    {
        Vector3 ret = new Vector3(-x, -y, -z);
        return ret;
    }

    public Vector3 Multiply( float scalar ) //Scalar multiplication
    {
        Vector3 ret = new Vector3(scalar * x, scalar * y, scalar * z);
        return ret;
    }
    public Vector3 MultiplyEqual( float scalar )
    {
        x *= scalar;
        y *= scalar;
        z *= scalar;
        return this;
    }

    public boolean IsEqual( Vector3 rhs ) //Equality check
    {
        return IsEqual(x, rhs.x) && IsEqual(y, rhs.y) && IsEqual(z, rhs.z);
    }
    public boolean IsNotEqual ( Vector3 rhs ) //Inequality check
    {
        return !IsEqual(x, rhs.x) || !IsEqual(y, rhs.y) || !IsEqual(z, rhs.z);
    }

    public Vector3 Assign( Vector3 rhs) //Assignment operator
    {
        x = rhs.x;
        y = rhs.y;
        z = rhs.z;
        return this;
    }

    public double Length() //Get magnitude
    {
        return Math.sqrt(x * x + y * y + z * z);
    }
    public float LengthSquared() //Get square of magnitude
    {
        return x * x + y * y + z * z;
    }

    public float Dot( Vector3 rhs ) //Dot product
    {
        return x * rhs.x + y * rhs.y + z * rhs.z;
    }
    public Vector3 Cross( Vector3 rhs ) //Cross product
    {
        Vector3 ret = new Vector3(y * rhs.z - z * rhs.y, z * rhs.x - x * rhs.z, x * rhs.y - y * rhs.x);
        return ret;
    }

    //Return a copy of this vector, normalized
    //Throw a divide by zero exception if normalizing a zero vector
    public Vector3 Normalized( ) throws DivideByZero
    {
        double d = Length();
        if(d <= EPSILON && -d <= EPSILON) { //throw new DivideByZero();
        }

        Vector3 ret = new Vector3((float)(x / d) , (float)(y / d), (float)(z / d));
        return ret;
    }

    //Normalize this vector and return a reference to it
    //Throw a divide by zero exception if normalizing a zero vector
    public Vector3 Normalize( ) throws DivideByZero
    {
        double d = Length();
        if(d <= EPSILON && -d <= EPSILON) { //throw new DivideByZero();
        }
        x /= d;
        y /= d;
        z /= d;
        return this;

    }
}

