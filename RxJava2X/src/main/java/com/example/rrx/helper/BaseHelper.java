package com.example.rrx.helper;

import com.zysdk.vulture.clib.utils.LoggerUtils;

import io.reactivex.Emitter;

public class BaseHelper {

    protected void log(String str) {
        LoggerUtils.loge(str);
    }

    protected void logThreadName(String str) {
        log(str + " , Thread id = " + Thread.currentThread().getName());
    }

    protected void sendDataByEmitter(Emitter<Integer> e, String prefix) {
        log(prefix + " subscribe");
        log("e onNext 1");
        e.onNext(1);
        log("e onNext 2");
        e.onNext(2);
        log("e onNext 3");
        e.onNext(3);
        log("e onNext 4");
        e.onNext(4);
        log("e onNext 5");
        e.onNext(5);
        e.onComplete();
    }
}
