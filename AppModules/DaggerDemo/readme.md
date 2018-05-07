

## ImmersionBar使用

    compile 'com.gyf.barlibrary:barlibrary:2.3.0'
    
    public class BaseActivity extends AppCompatActivity {
    
         private ImmersionBar mImmersionBar;
         @Override
         protected void onCreate(@Nullable Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           mImmersionBar = ImmersionBar.with(this);
           mImmersionBar = ImmersionBar.with(this);
           mImmersionBar.statusBarColor(R.color._0091ea);
           mImmersionBar.fitsSystemWindows(true);
           mImmersionBar.init();
           mImmersionBar.init();   //所有子类都将继承这些相同的属性
            
         }
        
         @Override
         protected void onDestroy() {
             super.onDestroy();
             if (mImmersionBar != null)
                mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
         }
    }
    
    
## lottie兼容性的问题解决(坑了我一天的时间)
    //解决支持库版本不兼容问题
    configurations.all {
        resolutionStrategy.eachDependency { DependencyResolveDetails details ->
            def requested = details.requested
            if (requested.group == 'com.android.support') {
                if (!requested.name.startsWith("multidex")) {
                    details.useVersion '25.3.0'
                }
            }
        }
    }
    
## toolbar中menu字体颜色修改
    <!--定义Toolbar样式-->
    <style name="ToolBarStyle" parent="@style/ThemeOverlay.AppCompat">
        <!--弹出菜单字体颜色-->
        <item name="actionMenuTextColor">@color/white</item>
    </style>
    
## checkbox自定义样式

    <CheckBox
    android:id="@+id/cb_customer"
    android:layout_width="wrap_content"
    style="@style/CustomerCheckboxStyle"
    android:layout_height="wrap_content" />
>    
    <style name="CustomerCheckboxStyle" parent="@android:style/Widget.CompoundButton.CheckBox">
    <item name="android:button">@drawable/selector_checkbox</item>
    </style>
>    
    <?xml version="1.0" encoding="utf-8"?>
    <selector xmlns:android="http://schemas.android.com/apk/res/android">
        <item android:drawable="@drawable/ease_dx_checkbox_on" android:state_pressed="true" />
        <item android:drawable="@drawable/ease_dx_checkbox_on" android:state_checked="true" />
        <item android:drawable="@drawable/ease_dx_checkbox_off" />
    </selector>
    
## 支持库封装    
    BaseDaggerSupportActivity
    BaseDaggerSupportFragment
    BaseDaggerSupportListActivity
    BaseDaggerSupportListFragment
    
    
## activity中控制menu的显示和隐藏

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_device, menu);
        this.menu = menu;
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                break;
        }
        return true;
    }
    
    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                menu.findItem(R.id.action_filter).setVisible(true);
                break;
            case 1:
                menu.findItem(R.id.action_filter).setVisible(false);
                break;
        }
    }

        
# 图表控件
https://github.com/wudashan/ChartView   (可选)  
   
#自定义控件
https://github.com/huangweicai/ChartView


## 优秀的图表控件
https://github.com/PhilJay/MPAndroidChart (可选)  
https://github.com/lecho/hellocharts-android
https://github.com/diogobernardino/WilliamChart

## ios版本
https://github.com/danielgindi/Charts

# MPAndroidChart使用教程 
https://blog.csdn.net/ww897532167/article/details/77334345
https://github.com/zhuanghongji/mp-android-chart-note/tree/master/MPAndroidSample-Temp
https://blog.csdn.net/u013855006/article/details/78702867 (让图表支持滑动)
http://www.bubuko.com/infodetail-1997545.html
https://blog.csdn.net/Dao_Li/article/details/52608319?locationNum=11&fps=1 (左右滑动支持)
https://blog.csdn.net/qq_26602021/article/details/59525947
https://blog.csdn.net/u014136472/article/details/50382292
https://blog.csdn.net/u014136472/article/details/50273309
https://blog.csdn.net/ppyyzz628/article/details/52096219
https://www.cnblogs.com/r-decade/p/6241693.html

##想要显示曲线图需要

    a).得到LineChart对象
    
    b).得到Entry对象，此处添加（X，Y）数据
    
    c).得到LineDataSet对象（一个LineDataSet对象代表一条线）
    
    d).得到LineData对象并添加LineDataSet对象
    
    e).设置数据，显示图表：lineChart.setData(lineData)

##注意事项

###X轴的刻度数量设置：
    xAxis.setLabelCount(xAxisValues.size(), true);
###X轴的位置显示
###//X轴设置显示位置在底部
    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
###线条描述Legend的显示位置等相关设置
###//折线图例 标签 设置
    Legend legend = lineChart.getLegend();
###//形状
    legend.setForm(Legend.LegendForm.LINE);
    legend.setTextSize(11f);
###//显示位置
    legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
    legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
    legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
    legend.setDrawInside(false);
###线显示的模式 曲线还是折线
###//线模式为圆滑曲线（默认折线）
    lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
###线圆点的设置
###//设置圆心半径
    lineDataSet.setCircleRadius(3f);
###//设置曲线值的圆点是实心还是空心
    lineDataSet.setDrawCircleHole(false);
### Y轴的起始点并没有挨着X轴
###//保证Y轴从0开始，不然会上移一点
    leftAxis.setAxisMinimum(0f);
    rightAxis.setAxisMinimum(0f);
###曲线显示的是float类型，怎样去除小数点呢？
###可以在设置 曲线 LineDataSet时更改数据类型，显示整数
    lineDataSet.setValueFormatter(new IValueFormatter() {
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            int IValue = (int) value;
            return String.valueOf(IValue);
        }
    });


# 其它图表控件
https://github.com/huangyanbin/SmartChart
https://github.com/whataa/SuitLines

图表常用功能及方法介绍：
XY轴的绘制

setEnabled(boolean enabled):设置轴是否被绘制。默认绘制,false不会被绘制。
setDrawLabels(boolean enabled):设置为true打开绘制轴的标签。
setDrawAxisLine(boolean enabled): 设置为true，绘制轴线
setDrawGridLines(boolean enabled): 设置为true绘制网格线。
定义轴线样式

setTextColor(int color): 设置轴标签文本颜色。
setTextSize(float size):设置轴标签的字体大小。
setTypeface(Typeface tf):设置轴标签的自定义Typeface（Typeface.createFromAsset(getAssets(), "字体文件名");）
setGridColor(int color): 设置网格线颜色。
setGridLineWidth(float width):设置网格线宽度。
setAxisLineColor(int color):设置此轴的坐标轴的颜色。
setAxisLineWidth(float width): 设置此轴的坐标轴的宽度。
setVisibleXRangeMaximum(float maxXRange):设置x轴最多显示数据条数，（要在设置数据源后调用，否则是无效的）
enableGridDashedLine(float lineLength, float spaceLength, float phase): 显示网格线虚线模式，"lineLength"控制短线条的长度，"spaceLength"控制两段线之间的间隔长度，"phase"控制开始的点。
图表交互设置

setTouchEnabled(boolean enabled): 允许你打开或者关闭与图表的所有触摸交互的情况。
setDragEnabled(boolean enabled): 打开或关闭对图表的拖动。
setScaleEnabled(boolean enabled):打开或关闭对图表所有轴的的缩放。
setScaleXEnabled(boolean enabled): 打开或关闭x轴的缩放
setScaleYEnabled(boolean enabled): 打开或关闭y轴的缩放。
setPinchZoom(boolean enabled): 如果设置为true，挤压缩放被打开。如果设置为false，x和y轴可以被单独挤压缩放。
setHighlightEnabled(boolean enabled): 如果设置为true，在图表中选中触屏高亮。
setHighlightPerDragEnabled(boolean enabled): 设置为true时允许高亮显示拖动结束的对象在缩放到最下时。默认：true
setHighlightIndicatorEnabled(boolean enabled): 如果设置为true， 指标线（或杆）将展示被选择的线的绘制的值。
自定义轴线的值
setAdjustXLabels(boolean enabled):如果被设置为true，x轴条目将依赖于它自己在进行缩放的时候。如果设置为false，x轴条目将总是保持相同。
setAvoidFirstLastClipping(boolean enabled):如果设置为true，图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘。
setSpaceBetweenLabels(int characters): 设置x轴标签之间的空间字符数，默认是4个。
setPosition(XAxisPosition pos):设置XAxis应该出现的位置。可以选择TOP，BOTTOM，BOTH_SIDED，TOP_INSIDE或者BOTTOM_INSIDE。
setStartAtZero(boolean enabled):如果这个打开，轴线总是有最小值0,无论什么类型的图表被展示。
setAxisMaxValue(float max):设置一个自定义的最大值为这条轴，如果设置了，这个值将不会依赖于提供的数据自动计算。
resetAxisMaxValue(): 调用这个将撤销以前设置的最大值。这意味着，你将再次允许轴自动计算它的最大值。
setAxisMinValue(float min): 设置一个自定义的最小值。如果设置了，这个值将不会依赖于你提供的数据进行自动计算。
resetAxisMinValue():调用这个方法撤销以前设置的最小值。这意味着，你将再次允许轴自动计算他的最小值。
setInverted(boolean enabled): 如果设置为true，这个轴将被反向，那意味着最高出的将到底部，最低部的到顶端。
setSpaceTop(float percent):设置在图表上最高处的值相比轴上最高值的顶端空间（总轴范围的百分比）
setSpaceBottom(float percent): 设置在图表上最低处的值相比轴上最低处值的底部空间（总轴范围的百分比）
setShowOnlyMinMax(boolean enabled): 如果打开了，这个轴将展示出它的最小值和最大值。这将忽略或者覆盖定义过的label-count。
setPosition(YAxisLabelPosition pos):设置轴标签应该被绘制的位置。INSIDE_CHART或者OUTSIDE_CHART中的一个。 自定义影响轴的数值范围应该在图表被设置数据之前应用。