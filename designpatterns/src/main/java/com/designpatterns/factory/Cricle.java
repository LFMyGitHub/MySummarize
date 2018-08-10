package com.designpatterns.factory;

import com.common.utils.CommonLogger;

import java.util.logging.Logger;

/**
 * 【类功能说明】
 * ...
 * File: Cricle.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/10
 * Changes (from 2018/8/10)
 * -------------------------------------------------------
 * 2018/8/10:创建Cricle.java(longfeng)
 * -------------------------------------------------------
 */
public class Cricle implements Shape {
    @Override
    public void draw() {
        CommonLogger.d("Cricle");
    }
}
