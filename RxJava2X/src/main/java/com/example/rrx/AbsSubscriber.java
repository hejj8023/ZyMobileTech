package com.example.rrx;

import com.zysdk.vulture.clib.utils.LoggerUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public abstract class AbsSubscriber<T> implements Subscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {
        LoggerUtils.loge("AbsSubscriber onSubscribe");
    }

    @Override
    public void onError(Throwable t) {
        LoggerUtils.loge("AbsSubscriber onError");
    }

    @Override
    public void onComplete() {
        LoggerUtils.loge("AbsSubscriber onComplete");
    }
}
