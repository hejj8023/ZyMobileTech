package com.example.rrx;

import com.zysdk.vulture.clib.utils.LoggerUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class AbsObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
        LoggerUtils.loge("AbsObserver onSubscribe");
    }

    @Override
    public void onError(Throwable e) {
        LoggerUtils.loge("AbsObserver onError");
    }

    @Override
    public void onComplete() {
        LoggerUtils.loge("AbsObserver onComplete");
    }
}
