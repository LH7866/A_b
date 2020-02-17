package com.lenovo.manufacture.zhy.Bean.four;

public class ProductBean {
        /**
         * id : 2461
         * userWorkId : 1
         * stageId : 5
         * productionLineId : 2
         * type : 0
         * position : 3
         * isAI : 0
         */

        private String id;
        private String userWorkId;
        private String stageId;
        private String productionLineId;
        private String type;
        private String position;
        private String isAI;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserWorkId() {
            return userWorkId;
        }

        public void setUserWorkId(String userWorkId) {
            this.userWorkId = userWorkId;
        }

        public String getStageId() {
            return stageId;
        }

    @Override
    public String toString() {
        return "ProductBean{" +
                "id='" + id + '\'' +
                ", userWorkId='" + userWorkId + '\'' +
                ", stageId='" + stageId + '\'' +
                ", productionLineId='" + productionLineId + '\'' +
                ", type='" + type + '\'' +
                ", position='" + position + '\'' +
                ", isAI='" + isAI + '\'' +
                '}';
    }

    public void setStageId(String stageId) {
            this.stageId = stageId;
        }

        public String getProductionLineId() {
            return productionLineId;
        }

        public void setProductionLineId(String productionLineId) {
            this.productionLineId = productionLineId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getIsAI() {
            return isAI;
        }

        public void setIsAI(String isAI) {
            this.isAI = isAI;
        }
    }
