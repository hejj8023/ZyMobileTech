package com.example.algorithm;

import com.example.algorithm.listeners.ICostCalc;

/**
 * Created by example on 2018/3/5.
 */

public class DefaultCostCalc implements ICostCalc {

    private long sTime;
    private String mStr;

    @Override
    public void start(String str) {
        this.mStr = str;
        sTime = System.nanoTime();
    }

    @Override
    public void end() {
        long eTime = System.nanoTime();
        System.out.println(mStr + " costtime = " + (eTime - sTime) + " 纳秒");
    }
}
