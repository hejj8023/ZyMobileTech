# android自定义dialog
## sytle:

    <style name="my_dialog_style" parent="android:Theme.Dialog">
        <!-- 背景颜色及透明程度 -->
        <item name="android:windowBackground">@android:color/transparent</item>
        <!-- 是否半透明 -->
        <item name="android:windowIsTranslucent">true</item>
        <!-- 是否没有标题 -->
        <item name="android:windowNoTitle">true</item>
        <!-- 是否浮现在activity之上 -->
        <item name="android:windowIsFloating">true</item>
        <!-- 是否背景模糊 -->
        <item name="android:backgroundDimEnabled">true</item>
        <!-- 设置背景模糊的透明度-->
        <item name="android:backgroundDimAmount">0.5</item>
    
        <!-- 动画 -->
        <item name="android:windowAnimationStyle">@style/dialog_animation</item>
    
        <item name="android:windowFrame">@null</item>
        <item name="android:background">@color/trans</item>
        <item name="android:windowContentOverlay">@null</item>
    </style>
    <!-- 对话框显示和退出动画 -->
    <style name="dialog_animation">
        <item name="android:windowEnterAnimation">@anim/dialog_enter</item>
        <item name="android:windowExitAnimation">@anim/dialog_exit</item>
    </style>

    
## dialog类;

    public class PlayModeSelectDialog extends Dialog {
        public PlayModeSelectDialog(@NonNull Context context) {
            super(context, R.style.my_dialog_style);
    
            View dialogView = UiUtils.inflateView(R.layout.layout_select_play_mode_dialog);
            setContentView(dialogView);
    
            Window dialogWindow = getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
            lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
            lp.height = (int) (d.heightPixels * 0.4); // 高度设置为屏幕的0.6
            dialogWindow.setAttributes(lp);
        }
    }
    
## checkbox的坑，如果只想在选择的时候显示图片，未选择不显示，最好是用图片来实现不要使用checkbox
如果一定要用checkbox使用下面的方式修改

    <CheckBox
        android:id="@+id/cb_customer_group"
        android:layout_width="@dimen/dp_30"
        android:layout_height="@dimen/dp_30"
        android:layout_alignParentRight="true"
        android:layout_centerVertical="true"
        android:background="@drawable/selector_checkbox"
        android:button="@null"
        android:checked="false"
        android:clickable="false" />
        
    <?xml version="1.0" encoding="utf-8"?>
    <selector xmlns:android="http://schemas.android.com/apk/res/android">
        <item android:drawable="@drawable/ic_checked" android:state_checked="true" />
        <item android:drawable="@drawable/d_trans" android:state_checked="false" />
        <item android:drawable="@drawable/d_trans" />
    </selector>