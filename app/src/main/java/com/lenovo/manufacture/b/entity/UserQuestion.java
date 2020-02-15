package com.lenovo.manufacture.b.entity;

import java.util.List;

public class UserQuestion {
    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":780,"userWorkId":1,"carId":1,"userProductionLineId":2460},{"id":779,"userWorkId":1,"carId":1,"userProductionLineId":2460},{"id":778,"userWorkId":1,"carId":1,"userProductionLineId":2460},{"id":777,"userWorkId":1,"carId":1,"userProductionLineId":2460},{"id":776,"userWorkId":1,"carId":1,"userProductionLineId":2460},{"id":775,"userWorkId":1,"carId":2,"userProductionLineId":2461},{"id":774,"userWorkId":1,"carId":1,"userProductionLineId":2460},{"id":781,"userWorkId":1,"carId":1,"userProductionLineId":2460},{"id":782,"userWorkId":1,"carId":1,"userProductionLineId":2460},{"id":783,"userWorkId":1,"carId":1,"userProductionLineId":2460}]
     */

    private int status;
    private String message;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 780
         * userWorkId : 1
         * carId : 1
         * userProductionLineId : 2460
         */

        private int id;
        private int userWorkId;
        private int carId;
        private int userProductionLineId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserWorkId() {
            return userWorkId;
        }

        public void setUserWorkId(int userWorkId) {
            this.userWorkId = userWorkId;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public int getUserProductionLineId() {
            return userProductionLineId;
        }

        public void setUserProductionLineId(int userProductionLineId) {
            this.userProductionLineId = userProductionLineId;
        }
    }
}
