package com.example.uat;

import android.app.Instrumentation;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MKTest {
    public Instrumentation instrumentation;

    public UiDevice uiDevice;

    /**
     * 在用例运行之前执行
     */
    @Before
    public void setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        uiDevice = UiDevice.getInstance(instrumentation);
    }

    @Test
    public void demoeTest() throws RemoteException {
        // 按下最近任务键
        uiDevice.pressRecentApps();
    }
}
