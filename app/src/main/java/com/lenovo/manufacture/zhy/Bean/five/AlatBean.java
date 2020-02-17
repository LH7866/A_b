package com.lenovo.manufacture.zhy.Bean.five;

public class AlatBean {
    private  String id;
    private String cardName;
    private  String content;
    private  String  productionLineName;
    private String userProductionLineId;
    private  String repairGold;

    @Override
    public String toString() {
        return "AlatBean{" +
                "id='" + id + '\'' +
                ", cardName='" + cardName + '\'' +
                ", content='" + content + '\'' +
                ", productionLineName='" + productionLineName + '\'' +
                ", userProductionLineId='" + userProductionLineId + '\'' +
                ", repairGold='" + repairGold + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProductionLineName() {
        return productionLineName;
    }

    public void setProductionLineName(String productionLineName) {
        this.productionLineName = productionLineName;
    }

    public String getUserProductionLineId() {
        return userProductionLineId;
    }

    public void setUserProductionLineId(String userProductionLineId) {
        this.userProductionLineId = userProductionLineId;
    }

    public String getRepairGold() {
        return repairGold;
    }

    public void setRepairGold(String repairGold) {
        this.repairGold = repairGold;
    }
}
