package com.lenovo.manufacture.b.entity;

public class BuildLine {
    /**
     * status : 400
     * message : 该位置已存在生产线
     */

    private int status;
    private String message;

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
}
