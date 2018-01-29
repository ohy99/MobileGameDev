package com.mgd.mgd;

/**
 * Created by HongYu
 */

public interface Collidable {
    String GetType();
    float GetPosX();
    float GetPosY();
    float GetRadius();

    void OnHit(Collidable _other);
}
