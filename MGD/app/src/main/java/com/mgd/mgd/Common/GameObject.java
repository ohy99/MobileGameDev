package com.mgd.mgd.Common;

import com.mgd.mgd.Components.ComponentBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.Vector;

/**
 * Created by HongYu
 */

public class GameObject{

    protected Map<String, ComponentBase> components = new HashMap<>();

    public void Init(){

    }
    public void Update(double dt) {
        for (Map.Entry<String ,ComponentBase> entry : components.entrySet())
        {
            entry.getValue().Update(dt);
        }
    }

    public void Destroy(){

    }

    public ComponentBase GetComponent(String str) {
        return components.get(str);
    }
}

