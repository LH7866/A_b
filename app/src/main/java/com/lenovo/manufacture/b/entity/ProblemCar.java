package com.lenovo.manufacture.b.entity;

public class ProblemCar {
    public String id;
    public String type;
    public String des;
    public String build;
    public String repair;

    public ProblemCar(String id, String type, String des, String build, String repair) {
        this.id = id;
        this.type = type;
        this.des = des;
        this.build = build;
        this.repair = repair;
    }
}
