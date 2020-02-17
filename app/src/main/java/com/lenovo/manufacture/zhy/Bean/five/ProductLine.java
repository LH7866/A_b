package com.lenovo.manufacture.zhy.Bean.five;

public class ProductLine {
        /**
         * id : 1
         * productionLineName : 轿车车型生产线
         * content : 生产轿车汽车
         * carId : 1
         */

        private String id;
        private String productionLineName;
        private String content;
        private String carId;

        public String getId() {
            return id;
        }

    @Override
    public String toString() {
        return "ProductLine{" +
                "id='" + id + '\'' +
                ", productionLineName='" + productionLineName + '\'' +
                ", content='" + content + '\'' +
                ", carId='" + carId + '\'' +
                '}';
    }

    public void setId(String id) {
            this.id = id;
        }

        public String getProductionLineName() {
            return productionLineName;
        }

        public void setProductionLineName(String productionLineName) {
            this.productionLineName = productionLineName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }
    }

