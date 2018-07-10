package com.example.ndk;

public class Lesson {
    static {
        System.loadLibrary("lesson");
    }

    public native String getString();
}
