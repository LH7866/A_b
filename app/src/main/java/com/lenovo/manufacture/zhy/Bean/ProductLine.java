package com.lenovo.manufacture.zhy.Bean;

public class ProductLine {
private  String productionLineName;
private  String content;

    @Override
    public String toString() {
        return "ProductLine{" +
                "productionLineName='" + productionLineName + '\'' +
                ", content='" + content + '\'' +
                '}';
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
}
