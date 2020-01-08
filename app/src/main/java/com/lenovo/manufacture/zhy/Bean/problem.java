package com.lenovo.manufacture.zhy.Bean;


public class problem {
    @Override
    public String toString() {
        return "problem{" +
                "id='" + id + '\'' +
                ", userProductionLineId=" + userProductionLineId +
                '}';
    }

    /*
         * id : 747
         * userWorkId : 1
         * carId : 1
         * userProductionLineId : 2438
         */
        private String id;
        private String userProductionLineId;
        public String getId() {
            return this.id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getUserProductionLineId() {
            return this.userProductionLineId;
        }
        public void setUserProductionLineId(String userProductionLineId) {
            this.userProductionLineId = userProductionLineId;
        }
    }
