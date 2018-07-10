package com.example.imoocproguard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ndk.Lesson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClassTwo classTwo = new ClassTwo();
        ClassThree classThree = new ClassThree();
        ClassView classView = new ClassView(this);
        Lesson lesson = new Lesson();

        Bundle bundle = new Bundle();
        try {
            bundle.putParcelable("key", new Book());
            function(null);
            ClassOne.functionA();
            classTwo.functionB();
            classThree.functionC();
            classView.getTitle();
            lesson.getString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void function(View view) {
        Log.e("test", "function");
        LogUtils.e("function");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("test", "onStart");
        LogUtils.e("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("test", "onResume");
        LogUtils.e("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("test", "onPause");
        LogUtils.e("onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("test", "onDestroy");
        LogUtils.e("onDestroy");
    }
}
