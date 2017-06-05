package com.chhd.cniaoplay.bean;

/**
 * Created by CWQ on 2017/5/11.
 */

public class BaseBean<T> {

    private final int REQUEST_SUCCESS = 1;

    private int status;
    private String message;
    private T data;

    private boolean isRequestSuccess;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRequestSuccess() {
        return getStatus() == REQUEST_SUCCESS;
    }
}
