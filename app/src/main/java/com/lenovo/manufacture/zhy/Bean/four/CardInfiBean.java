package com.lenovo.manufacture.zhy.Bean.four;

public class CardInfiBean {


        /**
         * id : 1
         * gold : 2000
         * area : 6
         * repairGold : 50
         */

        private String repairGold;

    public String getRepairGold() {
        return repairGold;
    }

    @Override
    public String toString() {
        return "CardInfiBean{" +
                "repairGold='" + repairGold + '\'' +
                '}';
    }

    public void setRepairGold(String repairGold) {
        this.repairGold = repairGold;
    }
}
