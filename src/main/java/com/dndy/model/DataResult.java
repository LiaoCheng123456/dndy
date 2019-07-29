package com.dndy.model;

import com.dndy.util.Const;

public class DataResult {

    private boolean success;

    private Object data;

    private String message;

    public DataResult(String message) {
        this.success = false;
        this.message = message;
    }

    public DataResult() {
        this.success = true;
        this.message = Const.SUCCESS;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
