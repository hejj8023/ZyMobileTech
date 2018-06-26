package com.example.comicbook;


import com.tencent.bugly.crashreport.CrashReport;
import com.zysdk.vulture.clib.corel.BaseApp;

public class ComicBookApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "bfbdfa9a27", true);
    }

    @Override
    protected String getLogTag() {
        return "ComicBook";
    }
}
