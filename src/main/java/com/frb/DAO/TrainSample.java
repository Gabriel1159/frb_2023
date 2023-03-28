package com.frb.DAO;

public class TrainSample {
    private String tName;
    private String tAttr;
    private String tSubTime;
    private String tPath;
    private int tState;
    private String tData;

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettAttr() {
        return tAttr;
    }

    public void settAttr(String tAttr) {
        this.tAttr = tAttr;
    }

    public String gettSubTime() {
        return tSubTime;
    }

    public void settSubTime(String tSubTime) {
        this.tSubTime = tSubTime;
    }

    public String gettPath() {
        return tPath;
    }

    public void settPath(String tPath) {
        this.tPath = tPath;
    }

    public int gettState() {
        return tState;
    }

    public void settState(int tState) {
        this.tState = tState;
    }

    public String gettData() {
        return tData;
    }

    public void settData(String tData) {
        this.tData = tData;
    }

    public TrainSample(String tName, String tAttr, String tSubTime, String tPath, int tState, String tData) {
        this.tName = tName;
        this.tAttr = tAttr;
        this.tSubTime = tSubTime;
        this.tPath = tPath;
        this.tState = tState;
        this.tData = tData;
    }
}
