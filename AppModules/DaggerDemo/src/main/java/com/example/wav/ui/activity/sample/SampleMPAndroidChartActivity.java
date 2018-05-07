package com.example.wav.ui.activity.sample;

import android.graphics.Color;

import com.example.wav.R;
import com.example.wav.bean.DevHistoryBean;
import com.example.wav.manager.LineChartManager;
import com.github.mikephil.charting.charts.LineChart;
import com.zhiyangstudio.commonlib.corel.BaseToolbarSupportActivity;
import com.zhiyangstudio.commonlib.utils.FileUtils;
import com.zhiyangstudio.commonlib.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by example on 2018/5/7.
 */

public class SampleMPAndroidChartActivity extends BaseToolbarSupportActivity {

    @BindView(R.id.line_chart1)
    LineChart mLineChart1;
    @BindView(R.id.line_chart2)
    LineChart mLineChart2;

    @Override
    protected boolean initToolBar() {
        return true;
    }

    @Override
    protected int getToolbarBgColor() {
        return R.color._0091ea;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_sample_mpchart;
    }


    @Override
    public void initData() {
        String jsonStr = FileUtils.getContentByAssets("devhistory.json");
        DevHistoryBean historyBean = GsonUtils.toObject(jsonStr, DevHistoryBean.class);

        LineChartManager lineChartManager1 = new LineChartManager(mLineChart1);
        LineChartManager lineChartManager2 = new LineChartManager(mLineChart2);

        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            xValues.add((float) i);
        }

        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 0; j <= 10; j++) {
                yValue.add((float) (Math.random() * 80));
            }
            yValues.add(yValue);
        }

        //颜色集合
        List<Integer> colours = new ArrayList<>();
        colours.add(Color.GREEN);
        colours.add(Color.BLUE);
        colours.add(Color.RED);
        colours.add(Color.CYAN);

        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("折线一");
        names.add("折线二");
        names.add("折线三");
        names.add("折线四");

        DevHistoryBean.V52Bean v52 = historyBean.getV52();
        List<Integer> list = v52.getL1();
        //创建多条折线的图表
        lineChartManager1.showLineChart(xValues, yValues.get(0), names.get(1), colours.get(3));
        lineChartManager1.setDescription("温度");
        lineChartManager1.setYAxis(100, 0, 11);
//        lineChartManager1.setHightLimitLine(70,"高温报警",Color.RED);

//        lineChartManager2.showLineChart(xValues, yValues, names, colours);
//        lineChartManager2.setYAxis(100, 0, 11);
//        lineChartManager2.setDescription("温度");
    }
}
