package com.example.scounter.corel;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.example.algorithm.utils.LogListener;
import com.example.algorithm.utils.LoggerUtils;

/**
 * Created by example on 2018/3/2.
 * <p>
 * 算法的主要部分,检测是否是步点
 */

public class StepDetector implements SensorEventListener, LogListener {
    private StepCount mStepCount;
    // 存放三轴数据
    private float[] oriValues = new float[3];
    // 当前传感器的值
    private float gravityNew;
    // 上次传感器的
    private float gravityOld;
    // 是否上升的标志位
    private boolean isDirectionUp;
    // 上一点的状态，上升还是下降
    private boolean lastStatus;
    // 持续上升次数
    private int continueUpCount;
    // 上一点的持续上升的次数，为了记录波峰的上升次数
    private int continueUpFormerCount;
    // 波谷值
    private float valleyOfWave;
    // 波峰值
    private float peakOfWave;
    //此次波峰的时间
    private long timeOfThisPeak;
    //上次波峰的时间
    private long timeOfLastPeak;
    //当前的时间
    private long timeOfNow;
    //波峰波谷时间差
    private long timeInterval = 250;
    //动态阈值需要动态的数据，这个值用于这些动态数据的阈值
    private float initialValue = (float) 1.3;
    //初始阈值
    float threadValue = (float) 2.0;
    int tempCount;
    private int valueNum = 4;
    // 用于存放计算阈值的波峰波谷差值
    private float[] tempValue = new float[valueNum];

    @Override
    public void onSensorChanged(SensorEvent event) {
        LoggerUtils.loge(this, "onSensorChanged");

        for (int i = 0; i < 3; i++) {
            oriValues[i] = event.values[i];
        }

        gravityNew = (float) Math.sqrt(oriValues[0] * oriValues[0] +
                oriValues[1] * oriValues[1] + oriValues[2] * oriValues[2]);

        detectorNewStep(gravityNew);
    }

    /**
     * 检测步子 ,并开始计算
     * 1.传入sensor中的数据
     * 2.如果检测到了波峰，并且符合时间差以及阈值的条件，则判断为1步
     * 3.符合时间差条件，波峰波谷差值大于initialValue,则将该差值纳入阈值中的计算中
     *
     * @param values
     */
    private void detectorNewStep(float values) {
        LoggerUtils.loge(this, "gravityNew = " + gravityNew);

        if (gravityOld == 0) {
            gravityOld = values;
        } else {
            LoggerUtils.loge(this, "timeOfLastPeak = " + timeOfLastPeak + " , timeOfThisPeak = "
                    + timeOfThisPeak);

            // TODO: 2018/3/2 检测波峰
            LoggerUtils.loge(this, "检测波峰");
            if (detectorPeak(values, gravityOld)) {
                timeOfLastPeak = timeOfThisPeak;
                timeOfNow = System.currentTimeMillis();

                if (timeOfNow - timeOfLastPeak >= timeInterval &&
                        (peakOfWave - valleyOfWave >= threadValue)) {
                    timeOfThisPeak = timeOfNow;
                    /**
                     * 更新界面的处理,不涉及到算法
                     *  一般在通知更新界面之前，增加下面处理，为了处理无效运动:
                     *  1.连续记录10才开始
                     *  2.例如记录的9步用户停信超过3秒，则前面的记录失效，下次从头开始
                     *  3.连续记录了9步用户还在运动，之前的数据才有效
                     */
                    // TODO: 2018/3/2 通知界面刷新
                    mStepCount.countStep();
                }

                LoggerUtils.loge(this, "计算阈值");
                if (this.timeOfNow - timeOfLastPeak >= timeInterval &&
                        (peakOfWave - valleyOfWave >= initialValue)) {
                    LoggerUtils.loge(this, " process peakValleyThread");
                    timeOfThisPeak = timeOfNow;
                    threadValue = peakValleyThread(peakOfWave - valleyOfWave);
                    LoggerUtils.loge(this, " threadValue = " + threadValue);
                }
            }
        }
        gravityOld = values;
    }

    /**
     * 阈值的计算
     * 1.通过波峰波谷的差值计算阈值
     * 2.记录4个值，存入tempVlaue[]中
     * 3.再净数组传入函数averageValue中计算阈值
     *
     * @param v
     * @return
     */
    private float peakValleyThread(float v) {
        LoggerUtils.loge(this, "peakValleyThread");
        float tempThread = threadValue;
        if (tempCount < valueNum) {
            LoggerUtils.loge(this, "tempCount < valueNum");
            tempValue[tempCount] = v;
            tempCount++;
        } else {
            LoggerUtils.loge(this, "tempCount ! < valueNum");
            tempThread = avarageValue(tempValue, valueNum);
            LoggerUtils.loge(this, "avarageValue tempThread = " + tempThread);
            for (int i = 1; i < valueNum; i++) {
                tempValue[i - 1] = tempValue[1];
            }
            tempValue[valueNum - 1] = v;
        }
        return tempThread;
    }

    /**
     * 梯度化阈值
     *
     * @param value
     * @param num
     * @return
     */
    private float avarageValue(float[] value, int num) {
        LoggerUtils.loge(this, "avarageValue");
        float avg = 0;

        for (int i = 0; i < num; i++) {
            avg += value[i];
        }

        LoggerUtils.loge(this, "avg = " + avg);
        avg = avg / valueNum;
        LoggerUtils.loge(this, "avg = " + avg);
        if (avg >= 8) {
            avg = 4.3f;
        } else if (avg >= 7 && avg < 8) {
            avg = 3.3f;
        } else if (avg >= 4 && avg < 7) {
            avg = 2.3f;
        } else if (avg >= 3 && avg < 4) {
            avg = 2.0f;
        } else {
            avg = 1.3f;
        }
        LoggerUtils.loge(this, "avg = " + avg);
        return avg;
    }

    /**
     * 检测波峰
     * 以下四个条件判断为波峰:
     * 1.目前点为下降的趋势:isDirectionUp为false
     * 2.之前的点为上升的趋势:lastStatus为true
     * 3.到波峰为止，持续上升大于等于2次
     * 4.波峰值大于20
     * 记录波谷值
     * 1.观察波形图，可以发现在出现步子的地方，波谷的下一个就是波峰，有比较明显的特征以及差值
     * 2.所以要记录每次的波谷值，为了和下次的波峰做对比
     *
     * @param newVlue
     * @param oldValue
     * @return
     */
    private boolean detectorPeak(float newVlue, float oldValue) {
        LoggerUtils.loge(this, "detectorPeak");
        lastStatus = isDirectionUp;
        if (newVlue >= oldValue) {
            LoggerUtils.loge(this, "newVlue >= oldValue");
            isDirectionUp = true;
            continueUpCount++;
        } else {
            LoggerUtils.loge(this, "newVlue ! >= oldValue");
            continueUpFormerCount = continueUpCount;
            continueUpCount = 0;
            isDirectionUp = false;
        }

        LoggerUtils.loge(this, "peakOfWave = " + peakOfWave + " , valleyOfWave = " + valleyOfWave);

        if (!isDirectionUp && lastStatus &&
                (continueUpFormerCount >= 2 || oldValue >= 20)) {
            peakOfWave = oldValue;
            LoggerUtils.loge(this, "peakOfWave = oldValue");
            return true;
        } else if (!lastStatus && isDirectionUp) {
            valleyOfWave = oldValue;
            LoggerUtils.loge(this, "valleyOfWave = oldValue");
            return false;
        }
        LoggerUtils.loge(this, "peakOfWave = " + peakOfWave + " , valleyOfWave = " + valleyOfWave);
        return false;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        LoggerUtils.loge(this, "onAccuracyChanged");
    }

    public void regListener(StepCount stepCount) {
        this.mStepCount = stepCount;
    }
}
