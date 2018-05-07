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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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

            DevHistoryBean.V52Bean v52 = historyBean.getV52();
            DevHistoryBean.V54Bean v54 = historyBean.getV54();
            DevHistoryBean.V58Bean v58 = historyBean.getV58();

            List<Integer> l1 = v52.getL1();
            List<String> dateArr = v52.getDateArr();
            List<Float> yValues = new ArrayList<>();
            HashMap<Integer, String> map = getMap(l1, dateArr, yValues);
            //设置x轴的数据
            ArrayList<Float> xValues = getXDatas(l1);
            //颜色集合
            List<Integer> colours = new ArrayList<>();
            colours.add(Color.GREEN);
            colours.add(Color.BLUE);
            colours.add(Color.RED);
            colours.add(Color.CYAN);

            //创建多条折线的图表
            mLineChartManager1.showLineChart(xValues, yValues, map, "aaaa", colours
                    .get(0));
            Integer max = Collections.max(l1);
            if (max == 0) {
                max = 4;
            }
            mLineChartManager1.setYAxis(max, Collections.min(l1),
                    4);

            l1 = v54.getL1();
            dateArr = v54.getDateArr();
            yValues = new ArrayList<>();
            map = getMap(l1, dateArr, yValues);
            //设置x轴的数据
            xValues = getXDatas(l1);
            //创建多条折线的图表
            mLineChartManager2.showLineChart(xValues, yValues, map, "", colours.get(1));
            max = Collections.max(l1);
            if (max == 0) {
                max = 4;
            }
            mLineChartManager2.setYAxis(max, Collections.min(l1),
                    4);

            l1 = v58.getL1();
            dateArr = v58.getDateArr();
            yValues = new ArrayList<>();
            map = getMap(l1, dateArr, yValues);
            //设置x轴的数据
            xValues = getXDatas(l1);
            //创建多条折线的图表
            mLineChartManager3.showLineChart(xValues, yValues, map, "", colours.get(2));
            max = Collections.max(l1);
            if (max == 0) {
                max = 4;
            }
            mLineChartManager3.setYAxis(max, Collections.min(l1),
                    4);
        }
    }

    public HashMap<Integer, String> getMap(List<Integer> l1, List<String> dateArr, List<Float>
            yValues) {
        HashMap<Integer, String> map = new HashMap<>();
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
