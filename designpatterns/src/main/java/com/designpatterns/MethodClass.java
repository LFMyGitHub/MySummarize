package com.designpatterns;

import com.designpatterns.singleton.CEO;
import com.designpatterns.singleton.Company;
import com.designpatterns.singleton.Staff;
import com.designpatterns.singleton.VP;

/**
 * 【类功能说明】
 * ...
 * File: MethodClass.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/5
 * Changes (from 2018/7/5)
 * -------------------------------------------------------
 * 2018/7/5:创建MethodClass.java(longfeng)
 * -------------------------------------------------------
 */
public class MethodClass {
    public void testSingleton() {
        Company company = new Company();
        Staff ceo1 = CEO.getCeo();
        Staff ceo2 = CEO.getCeo();
        company.addStaff(ceo1);
        company.addStaff(ceo2);
        Staff vp1 = new VP();
        Staff vp2 = new VP();
        Staff staff1 = new Staff();
        Staff staff2 = new Staff();
        company.addStaff(vp1);
        company.addStaff(vp2);
        company.addStaff(staff1);
        company.addStaff(staff2);
        company.showAllStaff();
        System.out.println();
    }
}
