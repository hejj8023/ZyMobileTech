

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