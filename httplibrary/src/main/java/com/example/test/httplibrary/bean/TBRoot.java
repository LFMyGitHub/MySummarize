package com.example.test.httplibrary.bean;

/**
 * 【类功能说明】
 * ...
 * File: TBRoot.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/13
 * Changes (from 2018/7/13)
 * -------------------------------------------------------
 * 2018/7/13:创建TBRoot.java(longfeng)
 * -------------------------------------------------------
 */
public class TBRoot {
    private int code;

    private TBDataBean data;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setData(TBDataBean data) {
        this.data = data;
    }

    public TBDataBean getData() {
        return this.data;
    }
}
