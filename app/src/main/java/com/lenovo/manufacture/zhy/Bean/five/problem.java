package com.lenovo.manufacture.zhy.Bean.five;


public class problem {

    @Override
    public String toString() {
        return "problem{" +
                "id='" + id + '\'' +
                ", carId='" + carId + '\'' +
                ", userProductionLineId='" + userProductionLineId + '\'' +
                '}';
    }

    /**
         * id : 777
         * userWorkId : 1
         * carId : 1
         * userProductionLineId : 2460
         */

        private String id;
        private String carId;
        private String userProductionLineId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getUserProductionLineId() {
        return userProductionLineId;
    }

    public void setUserProductionLineId(String userProductionLineId) {
        this.userProductionLineId = userProductionLineId;
    }
}

