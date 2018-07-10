package com.example.rrx;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SampleHelper2 {
    public SampleHelper2(Context context) {
    }

    /**
     * 上游可以发送无限个onNext, 下游也可以接收无限个onNext.
     * 当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送,
     * 而下游收到onComplete事件之后将不再继续接收事件.
     * 当上游发送了一个onError后, 上游onError之后的事件将继续发送,
     * 而下游收到onError事件之后将不再继续接收事件.
     * 上游可以不发送onComplete或onError.
     * 最为关键的是onComplete和onError必须唯一并且互斥, 即不能发多个onComplete,
     * 也不能发多个onError, 也不能先发一个onComplete, 然后再发一个onError, 反之亦然
     */
    public void doCreate() {
        //创建上游，被观察者，发射者
        //它可以发出三种类型的事件，通过调用emitter的onNext(T value)、
        // onComplete()和onError(Throwable error)就可以分别发出next事件、complete事件和error事件。

        //注: 关于onComplete和onError唯一并且互斥这一点,
        // 是需要自行在代码中进行控制, 如果你的代码逻辑中违背了这个规则,
        // **并不一定会导致程序崩溃. ** 比如发送多个onComplete是可以正常运行的,
        // 依然是收到第一个onComplete就不再接收了, 但若是发送多个onError,
        // 则收到第二个onError事件会导致程序会崩溃.
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            /**
             * emitter 发射器 它可以发出三种类型的事件，通过调用emitter的onNext(T value)、
             *    onComplete()和onError(Throwable error)
             *    就可以分别发出next事件、complete事件和error事件。
             *
             *  ---------------------------------注意如下--------------------------
             *
             *  1.上游可以发送无限个onNext, 下游也可以接收无限个onNext.
             *
             *  2.当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送,
             *    而下游收到onComplete事件之后将不再继续接收事件.
             *
             *  3.当上游发送了一个onError后, 上游onError之后的事件将继续发送,
             *    而下游收到onError事件之后将不再继续接收事件.
             *
             *  4.上游可以不发送onComplete或onError.
             *
             *  5.最为关键的是onComplete和onError必须唯一并且互斥, 即不能发多个onComplete,
             *    也不能发多个onError, 也不能先发一个onComplete, 然后再发一个onError, 反之亦然
             * @param e
             * @throws Exception
             */
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
        });

        //创建下游，观察者，
        Observer<Integer> observer = new Observer<Integer>() {
            public Disposable mDisposeable;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposeable = d;
            }

            @Override
            public void onNext(Integer i) {
                //调用dispose()并不会导致上游不再继续发送事件, 上游会继续发送剩余的事件.
                if (i == 2) {
                    mDisposeable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
}
