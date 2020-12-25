package com.shiro.common;

import java.util.List;

public class JsonData<T> {

    private boolean flag;//是否成功

    private String msg;//提示信息

    private T data; //数据



    private List<T> dataList;

    private Integer code;

    public JsonData(boolean flag, String msg, Integer code) {
        this.flag = flag;
        this.msg = msg;
        this.code = code;
    }

    public JsonData(boolean flag, String msg, T data, Integer code) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
        this.code = code;
    }


    public JsonData(boolean flag, String msg, List<T> dataList, Integer code) {
        this.flag = flag;
        this.msg = msg;
        this.dataList = dataList;
        this.code = code;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
