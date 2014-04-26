package com.dim.swimlap.objects;

import android.widget.Chronometer;

import java.util.ArrayList;

public final class Singleton {

    private static volatile Singleton instance = null;
    private Chronometer chronometer;

    private Singleton() {
        super();
    }

    public final static Singleton getInstance() {
        if (Singleton.instance == null) {
            synchronized (Singleton.class) {
                if (Singleton.instance == null) {
                    Singleton.instance = new Singleton();
                }
            }
        }
        return Singleton.instance;
    }



}

