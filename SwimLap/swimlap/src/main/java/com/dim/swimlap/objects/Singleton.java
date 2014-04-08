package com.dim.swimlap.objects;

import java.util.ArrayList;

public final class Singleton {

    private static volatile Singleton instance = null;

    private String xxx;
    private int zzz;
    private ArrayList<String> list;

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

    public void buildList() {
        this.list = new ArrayList<String>();
        list.add("first");
    }

    public void addToList(String st) {
        list.add(st);
    }
    public void clearList(){
        list.clear();
    }
    public ArrayList<String> getList(){
        return list;
    }

}

