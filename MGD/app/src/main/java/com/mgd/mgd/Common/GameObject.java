package com.mgd.mgd.Common;

import com.mgd.mgd.Components.ComponentBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.Vector;

public class GameObject{

    protected Map<String, ComponentBase> components = new HashMap<>();

    public void Init(){

    }
    public void Update(double dt) {

    }

    public void Destroy(){

    }

    public ComponentBase GetComponent(String str) {
        return components.get(str);
    }
}

