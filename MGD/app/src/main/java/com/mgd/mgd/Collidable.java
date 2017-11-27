package com.mgd.mgd;

public interface Collidable {
    String GetType();
    float GetPosX();
    float GetPosY();
    float GetRadius();

    void OnHit(Collidable _other);
}
