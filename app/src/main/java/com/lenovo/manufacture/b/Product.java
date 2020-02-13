package com.lenovo.manufacture.b;

public class Product {
    private String plStepName;
    private String power;
    private String consume;
    private String stageId;

    @Override
    public String toString() {
        return "Product{" +
                "plStepName='" + plStepName + '\'' +
                ", power='" + power + '\'' +
                ", consume='" + consume + '\'' +
                ", stageId='" + stageId + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;


    public String getPlStepName() {
        return plStepName;
    }

    public void setPlStepName(String plStepName) {
        this.plStepName = plStepName;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}
