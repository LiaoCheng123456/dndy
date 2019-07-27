package com.dndy.model;

public class ResultPageModel {
    private Integer totalSize;
    private Object totalResult;

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Object getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Object totalResult) {
        this.totalResult = totalResult;
    }
}
