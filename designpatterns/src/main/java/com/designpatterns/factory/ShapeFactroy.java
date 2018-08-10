package com.designpatterns.factory;

/**
 * 【类功能说明】
 * ...
 * File: ShapeFactroy.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/10
 * Changes (from 2018/8/10)
 * -------------------------------------------------------
 * 2018/8/10:创建ShapeFactroy.java(longfeng)
 * -------------------------------------------------------
 */
public class ShapeFactroy {

    public Shape getShape(String type) {
        switch (type) {
            case "Cricle":
                return new Cricle();
            case "Square":
                return new Square();
            case "Rectangle":
                return new Rectangle();
            default:
                return null;
        }
    }
}
