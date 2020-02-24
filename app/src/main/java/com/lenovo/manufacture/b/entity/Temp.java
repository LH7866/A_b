package com.lenovo.manufacture.b.entity;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class Temp extends LitePalSupport {

    public String getWorkshopTemp() {
        return workshopTemp;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "workshopTemp='" + workshopTemp + '\'' +
                ", outTemp='" + outTemp + '\'' +
                '}';
    }

    public void setWorkshopTemp(String workshopTemp) {
        this.workshopTemp = workshopTemp;
    }

    public String getOutTemp() {
        return outTemp;
    }

    public void setOutTemp(String outTemp) {
        this.outTemp = outTemp;
    }

    private String workshopTemp;
        private String outTemp;

    }

