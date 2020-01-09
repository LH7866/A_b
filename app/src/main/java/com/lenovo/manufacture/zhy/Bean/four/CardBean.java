package com.lenovo.manufacture.zhy.Bean.four;

public class CardBean {
        /**
         * id : 1
         * carName : 轿车汽车
         * content : 轿车汽车标准型
         */

        private String carName;
        private String content;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "CardBean{" +
                "carName='" + carName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public void setContent(String content) {
        this.content = content;
    }
}


