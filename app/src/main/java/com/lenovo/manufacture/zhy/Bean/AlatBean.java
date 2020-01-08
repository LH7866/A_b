package com.lenovo.manufacture.zhy.Bean;

public class AlatBean {
    private  String id;

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
    public String getRepairGold() {
        return repairGold;
    }

    public void setRepairGold(String repairGold) {
        this.repairGold = repairGold;
    }

    private String cardName;
    private  String content;
    private  String  productionLineName;
    private  String repairGold;
}
