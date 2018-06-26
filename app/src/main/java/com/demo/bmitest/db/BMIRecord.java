package com.demo.bmitest.db;

import org.litepal.crud.DataSupport;

public class BMIRecord extends DataSupport {
    private String nowTime;
    private double result;

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
