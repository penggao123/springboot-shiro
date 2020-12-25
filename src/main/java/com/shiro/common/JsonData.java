package com.shiro.common;

import java.util.List;

public class JsonData<T> {

    private boolean flag;//是否成功

    private String msg;//提示信息

    private T data; //数据



    private List<T> dataList;

    public JsonData() {

    }

    public JsonData(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public JsonData(boolean flag, String msg, T data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public JsonData(boolean flag, String msg, List<T> dataList) {
        this.flag = flag;
        this.msg = msg;
        this.dataList = dataList;
    }

    public JsonData(boolean flag, String msg, T data, List<T> dataList) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
        this.dataList = dataList;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "flag=" + flag +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", dataList=" + dataList +
                '}';
    }
}
