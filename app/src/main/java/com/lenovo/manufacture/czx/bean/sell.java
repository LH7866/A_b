package com.lenovo.manufacture.czx.bean;

public class sell {
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "sell{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", time=" + time +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String price;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    private String num;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

}
