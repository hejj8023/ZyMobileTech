# constraintlayout的使用

#RecyclerView添加分割线的简便方法

##添加Android自带的分割线

    recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    
    
## 设置自定义颜色

    <?xml version="1.0" encoding="utf-8"?>
    <shape xmlns:android="http://schemas.android.com/apk/res/android"
        android:shape="rectangle">
        <gradient
            android:centerColor="#0000ff"
            android:endColor="#00ff00"
            android:startColor="#ff0000"
            android:type="linear" />
        <size android:height="3dp" />
    </shape>
    
    defaultItemDecoration = new DividerItemDecoration(mContext, orientation);
    defaultItemDecoration.setDrawable(UiUtils.getDrawable(mContext, dividerResId));
    recyclerView.addItemDecoration(defaultItemDecoration);
    
## 万能分割线
    
    