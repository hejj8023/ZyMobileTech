package com.example.scounter.corel;

import com.example.algorithm.utils.LogListener;
import com.example.algorithm.utils.LoggerUtils;
import com.example.scounter.listeners.StepCountListener;
import com.example.scounter.listeners.StepVluePassListener;

/**
 * Created by example on 2018/3/2.
 */

public class StepCount implements StepCountListener, LogListener {
    private StepVluePassListener mListener;

    private long timeOfLastPeak = 0;
    private long timeOfThisPeak = 0;
    private int count;
    private int mCount;


    public void regListener(StepVluePassListener listner) {
        this.mListener = listner;
    }

    /**
     * 连续走10步才开始计步
     * 连续走了9步以下，保留超过3少，则计数清空
     */
    @Override
    public void countStep() {
        LoggerUtils.loge(this, "countStep");
        this.timeOfLastPeak = this.timeOfThisPeak;
        this.timeOfThisPeak = System.currentTimeMillis();
        if (this.timeOfThisPeak - this.timeOfLastPeak <= 3000L) {
            if (this.count < 9) {
                LoggerUtils.loge(this, "count < 9");
                this.count++;
            } else if (this.count == 9) {
                LoggerUtils.loge(this, "count == 9");
                this.count++;
                this.mCount += this.count;
                notifyListener();
            } else {
                LoggerUtils.loge(this, "count > 9");
                this.mCount++;
                notifyListener();
            }
        } else {
            //超时
            this.count = 1; // 为1，不是0
        }
    }

    private void notifyListener() {
        if (mListener != null) {
            mListener.stepChanged(this.mCount);
        }
    }

    public void setSteps(int value) {
        this.mCount = value;
        this.count = 0;
        timeOfLastPeak = 0;
        timeOfThisPeak = 0;
        notifyListener();
    }
}
