package com.example.wav.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.wav.R;
import com.example.wav.base.BaseAdvActivity;
import com.example.wav.bean.DevHistoryBean;
import com.example.wav.manager.LineChartManager;
import com.example.wav.mvp.contract.DDContract;
import com.example.wav.mvp.presenter.DDPresenter;
import com.github.mikephil.charting.charts.LineChart;
import com.zhiyangstudio.commonlib.utils.LoggerUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by example on 2018/5/7.
 */

public class DeviceDetailActivity extends BaseAdvActivity<DDPresenter, DDContract.IDDView>
        implements DDContract.IDDView {

    @BindView(R.id.line_chart1)
    LineChart mLineChart1;
    @BindView(R.id.line_chart2)
    LineChart mLineChart2;
    @BindView(R.id.line_chart3)
    LineChart mLineChart3;
    private String mDevId;
    private LineChartManager mLineChartManager1;
    private LineChartManager mLineChartManager2;
    private LineChartManager mLineChartManager3;

    @Override
    public void initView() {
        mLineChartManager1 = new LineChartManager(mLineChart1);
        mLineChartManager2 = new LineChartManager(mLineChart2);
        mLineChartManager3 = new LineChartManager(mLineChart3);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String devName = bundle.getString("devName");
        setTitle(devName);
        mDevId = bundle.getString("devID");

        mPresenter.getDevHistory();
    }

    @Override
    public void refreshUi() {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected boolean initToolBar() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_device_detail;
    }

    @Override
    public void addListener() {
    }

    @Override
    public String getDevId() {
        return mDevId;
    }

    @Override
    public void setData(DevHistoryBean historyBean) {
        if (historyBean != null) {

            DevHistoryBean.V54Bean v54 = historyBean.getV54();
            DevHistoryBean.V58Bean v58 = historyBean.getV58();

            DevHistoryBean.V52Bean v52 = historyBean.getV52();
            List<Integer> l1 = v52.getL1();
            List<Integer> l2 = v52.getL2();
            List<Integer> l3 = v52.getL3();
            List<Integer> l4 = v52.getL4();
            List<Integer> l5 = v52.getL5();
            List<String> dateArr = v52.getDateArr();
            List<List<Float>> yDataList = new ArrayList<>();
            List<Float> yValues = new ArrayList<>();
            HashMap<Integer, String> map = getMap(l1, dateArr, yValues);
            yDataList.add(yValues);

            if (l2 != null && l2.size() > 0) {
                yValues = new ArrayList<>();
                getMap(l2, dateArr, yValues);
                yDataList.add(yValues);
            }

            if (l3 != null && l3.size() > 0) {
                yValues = new ArrayList<>();
                getMap(l3, dateArr, yValues);
                yDataList.add(yValues);
            }

            if (l4 != null && l4.size() > 0) {
                yValues = new ArrayList<>();
                getMap(l4, dateArr, yValues);
                yDataList.add(yValues);
            }

            if (l5 != null && l5.size() > 0) {
                yValues = new ArrayList<>();
                getMap(l5, dateArr, yValues);
                yDataList.add(yValues);
            }


            //设置x轴的数据
            ArrayList<Float> xValues = getXDatas(l1);
            //颜色集合
            List<Integer> colours = new ArrayList<>();
            colours.add(Color.GREEN);
            colours.add(Color.BLUE);
            colours.add(Color.RED);
            colours.add(Color.CYAN);

            //创建多条折线的图表
            if (yDataList.size() == 1) {
                mLineChartManager1.showLineChart(xValues, yDataList.get(0), map, "aaaa", colours
                        .get(0));
            } else {
                mLineChartManager1.showMultiChart(xValues, yDataList, map, colours, false);
            }

            int[] maxV = new int[5];
            maxV[0] = Collections.max(l1);
            maxV[1] = l2 != null && l2.size() > 0 ? Collections.max(l2) : 0;
            maxV[2] = l3 != null && l3.size() > 0 ? Collections.max(l3) : 0;
            maxV[3] = l4 != null && l4.size() > 0 ? Collections.max(l4) : 0;
            maxV[4] = l5 != null && l5.size() > 0 ? Collections.max(l5) : 0;

            int max = maxV[0];
            for (int i = 0; i < maxV.length; i++) {
                LoggerUtils.loge("arr[i] = " + maxV[i]);
                if (maxV[i] > max) {
                    max = maxV[i];
                }
            }

            if (max == 0) {
                max = 4;
            }
            mLineChartManager1.setYAxis(max, Collections.min(l1), 4);


            l1 = v54.getL1();
            l2 = v54.getL2();
            l3 = v54.getL3();
            l4 = v54.getL4();
            l5 = v54.getL5();
            dateArr = v54.getDateArr();
            yDataList = new ArrayList<>();
            yValues = new ArrayList<>();
            map = getMap(l1, dateArr, yValues);
            yDataList.add(yValues);

            if (l2 != null && l2.size() > 0) {
                yValues = new ArrayList<>();
                getMap(l2, dateArr, yValues);
                yDataList.add(yValues);
            }

            if (l3 != null && l3.size() > 0) {
                yValues = new ArrayList<>();
                getMap(l3, dateArr, yValues);
                yDataList.add(yValues);
            }

            if (l4 != null && l4.size() > 0) {
                yValues = new ArrayList<>();
                getMap(l4, dateArr, yValues);
                yDataList.add(yValues);
            }

            if (l5 != null && l5.size() > 0) {
                yValues = new ArrayList<>();
                getMap(l5, dateArr, yValues);
                yDataList.add(yValues);
            }


            //设置x轴的数据
            xValues = getXDatas(l1);
            //颜色集合
            colours = new ArrayList<>();
            colours.add(Color.GREEN);
            colours.add(Color.BLUE);
            colours.add(Color.RED);
            colours.add(Color.CYAN);

            //创建多条折线的图表
            //创建多条折线的图表
            if (yDataList.size() == 1) {
                mLineChartManager2.showLineChart(xValues, yDataList.get(0), map, "aaaa", colours
                        .get(0));
            } else {
                mLineChartManager2.showMultiChart(xValues, yDataList, map, colours, false);
            }


            maxV = new int[5];
            maxV[0] = Collections.max(l1);
            maxV[1] = l2 != null && l2.size() > 0 ? Collections.max(l2) : 0;
            maxV[2] = l3 != null && l3.size() > 0 ? Collections.max(l3) : 0;
            maxV[3] = l4 != null && l4.size() > 0 ? Collections.max(l4) : 0;
            maxV[4] = l5 != null && l5.size() > 0 ? Collections.max(l5) : 0;

            max = maxV[0];
            for (int i = 0; i < maxV.length; i++) {
                LoggerUtils.loge("arr[i] = " + maxV[i]);
                if (maxV[i] > max) {
                    max = maxV[i];
                }
            }

            if (max == 0) {
                max = 4;
            }
            mLineChartManager2.setYAxis(max, Collections.min(l1), 4);

            l1 = v58.getL1();
            l2 = v58.getL2();
            l3 = v58.getL3();
            l4 = v58.getL4();
            l5 = v58.getL5();
            dateArr = v58.getDateArr();
            yDataList = new ArrayList<>();
            yValues = new ArrayList<>();
            map = getMap(l1, dateArr, yValues);
            yDataList.add(yValues);

            if (l2 != null && l2.size() > 0) {
                yValues = new ArrayList<>();
                getMap(l2, dateArr, yValues);
                yDataList.add(yValues);
            }

            if (l3 != null && l3.size() > 0) {
                yValues = new ArrayList<>();
                getMap(l3, dateArr, yValues);
                yDataList.add(yValues);
            }

            if (l4 != null && l4.size() > 0) {
                yValues = new ArrayList<>();
                getMap(l4, dateArr, yValues);
                yDataList.add(yValues);
            }

            if (l5 != null && l5.size() > 0) {
                yValues = new ArrayList<>();
                getMap(l5, dateArr, yValues);
                yDataList.add(yValues);
            }


            //设置x轴的数据
            xValues = getXDatas(l1);
            //颜色集合
            colours = new ArrayList<>();
            colours.add(Color.GREEN);
            colours.add(Color.BLUE);
            colours.add(Color.RED);
            colours.add(Color.CYAN);

            //创建多条折线的图表
            if (yDataList.size() == 1) {
                mLineChartManager3.showLineChart(xValues, yDataList.get(0), map, "aaaa", colours
                        .get(0));
            } else {
                mLineChartManager3.showMultiChart(xValues, yDataList, map, colours, true);
            }

            maxV = new int[5];
            maxV[0] = Collections.max(l1);
            maxV[1] = l2 != null && l2.size() > 0 ? Collections.max(l2) : 0;
            maxV[2] = l3 != null && l3.size() > 0 ? Collections.max(l3) : 0;
            maxV[3] = l4 != null && l4.size() > 0 ? Collections.max(l4) : 0;
            maxV[4] = l5 != null && l5.size() > 0 ? Collections.max(l5) : 0;

            max = maxV[0];
            for (int i = 0; i < maxV.length; i++) {
                LoggerUtils.loge("arr[i] = " + maxV[i]);
                if (maxV[i] > max) {
                    max = maxV[i];
                }
            }

            if (max == 0) {
                max = 4;
            }
            mLineChartManager3.setYAxis(max, Collections.min(l1), 4);

        }
    }

    public HashMap<Integer, String> getMap(List<Integer> l1, List<String> dateArr, List<Float>
            yValues) {
        HashMap<Integer, String> map = new LinkedHashMap<>();
        for (Integer i = 0; i < l1.size(); i++) {
            yValues.add(l1.get(i).floatValue());
            String value = dateArr.get(i);
            SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssss");
            try {
                Date date = sdfDateFormat.parse(value);
                SimpleDateFormat sdfDateFormat1 = new SimpleDateFormat("MM-dd");
                String timeStr = sdfDateFormat1.format(date);
                map.put(i, timeStr);
            } catch (ParseException e) {
                e.printStackTrace();
                map.put(i, "a");
            }
        }
        return map;
    }

    @NonNull
    private ArrayList<Float> getXDatas(List<Integer> l1) {
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i < l1.size(); i++) {
            xValues.add((float) i);
        }
        return xValues;
    }

}
