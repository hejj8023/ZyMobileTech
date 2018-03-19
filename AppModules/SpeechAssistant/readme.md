语音助手-基于百度sdk(永久免费，哈哈...)
  

参考资料

https://github.com/JZHowe/HoweAssistant

## 列表数据的获取

### 清单文件

    <activity
        android:name=".activity.speech.SpeechAllRecogActivity"
        android:label="全部识别功能">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="com.example.sa.intent.category.SAMPLE_CODE" />
        </intent-filter>
    
    </activity>
    <activity
        android:name=".activity.speech.SpeechAllSettingActivity"
        android:label="全部识别设置">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="com.example.sa.intent.category.SAMPLE_CODE" />
        </intent-filter>
    
    </activity>
    <activity
        android:name=".activity.speech.SpeechMiniRecogActivity"
        android:label="精简版识别">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="com.example.sa.intent.category.SAMPLE_CODE" />
        </intent-filter>
    
    </activity>
    
### 数据的获取

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> myData = new ArrayList<>();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(CATEGORY_SAMPLE_CODE);
    
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, 0);
    
        if (list == null) {
            return myData;
        }
    
        int len = list.size();
    
        ResolveInfo info;
        for (int i = 0; i < len; i++) {
            info = list.get(i);
            if (!getPackageName().equalsIgnoreCase(info.activityInfo.packageName)) {
                continue;
            }
    
            CharSequence labelSeq = info.loadLabel(packageManager);
            CharSequence desc = null;
            if (info.activityInfo.descriptionRes != 0) {
                desc = packageManager.getText(info.activityInfo.packageName, info.activityInfo
                        .descriptionRes, null);
                LoggerUtils.loge(this, "desc = " + desc);
            }
    
            String label = labelSeq != null ? labelSeq.toString() : info.activityInfo.name;
            addItem(myData, label, activityIntent(info.activityInfo.applicationInfo.packageName,
                    info.activityInfo.name), desc);
        }
    
        return myData;
    }
    
    private void addItem(List<Map<String, Object>> list, String label, Intent intent, CharSequence desc) {
        Map<String, Object> temp = new HashMap<>();
        temp.put("title", label);
        if (desc != null) {
            temp.put("description", desc.toString());
        }
        temp.put("intent", intent);
        list.add(temp);
    }
    
    private Intent activityIntent(String packageName, String name) {
        Intent intent = new Intent();
        intent.setClassName(packageName, name);
        return intent;
    }    