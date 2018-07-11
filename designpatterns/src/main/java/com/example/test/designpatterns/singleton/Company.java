package com.example.test.designpatterns.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * 【类功能说明】
 * 公司类
 * File: Company.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/5
 * Changes (from 2018/7/5)
 * -------------------------------------------------------
 * 2018/7/5:创建Company.java(longfeng)
 * -------------------------------------------------------
 */
public class Company {
    private List<Staff> allStaff = new ArrayList<Staff>();

    public void addStaff(Staff staff) {
        allStaff.add(staff);
    }

    public void showAllStaff() {
        for (Staff s : allStaff) {
            System.out.println("Obj:" + s.toString());
        }
    }
}
