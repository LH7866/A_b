package com.lenovo.manufacture.b;

public class Material {
    private String materialName;
    private String supplyName;
    private String num;
    private String price;

    @Override
    public String toString() {
        return "Material{" +
                "materialName='" + materialName + '\'' +
                ", supplyName='" + supplyName + '\'' +
                ", num=" + num +
                ", price=" + price +
                '}';
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
