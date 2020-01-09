package com.lenovo.manufacture.zhy.Bean.four;

public class StuBean {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StuBean{" +
                "productionLineId='" + productionLineId + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    /**
         * id : 2453
         * userWorkId : 1
         * stageId : 36
         * productionLineId : 1
         * type : 0
         * position : 2
         * isAI : 1
         */
        private String productionLineId;
        private String id;

        public String getProductionLineId() {
            return productionLineId;
        }

        public void setProductionLineId(String productionLineId) {
            this.productionLineId = productionLineId;
        }
    }

