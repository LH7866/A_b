package com.lenovo.manufacture.b.entity;

import java.util.List;

public class CarInfo {
    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":1,"gold":2000,"area":6,"repairGold":50},{"id":2,"gold":3000,"area":7,"repairGold":60},{"id":3,"gold":4000,"area":5,"repairGold":85}]
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
         * id : 1
         * gold : 2000
         * area : 6
         * repairGold : 50
         */

        private int id;
        private int gold;
        private int area;
        private int repairGold;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public int getRepairGold() {
            return repairGold;
        }

        public void setRepairGold(int repairGold) {
            this.repairGold = repairGold;
        }
    }
}
