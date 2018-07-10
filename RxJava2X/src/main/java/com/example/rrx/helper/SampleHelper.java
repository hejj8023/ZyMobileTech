package com.example.rrx.helper;

import android.content.Context;
import android.support.annotation.NonNull;

import com.zysdk.vulture.clib.utils.LoggerUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SampleHelper {
    private Context mContext;

    public SampleHelper(Context context) {
        this.mContext = context;
    }

    public void create() {
        log("create");
        // TODO: 2018/7/9 创建一个上游的 Observable
        Observable<String> observable = Observable.create(getSampleObservable());

        // TODO: 2018/7/9 创建一个下游的Observer
        Observer<String> observer = getSampleObserver();

        // TODO: 2018/7/9 建立连接
        observable.subscribe(observer);
    }

    private void log(String str) {
        LoggerUtils.loge(str);
    }

    public void create2() {
        log("create2");
        Observable.create(getSampleObservable()).subscribe(getSampleObserver());
    }

    @NonNull
    private ObservableOnSubscribe<String> getSampleObservable() {
        return new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                log("observable subscribe");
                log("observable onNext");
                e.onNext("hello world");
                log("observable onComplete");
                e.onComplete();
            }
        };
    }

    @NonNull
    private Observer<String> getSampleObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                log("observer onSubscribe");
            }

            @Override
            public void onNext(String s) {
                log("observer onNext");
                log("observer onNext str = " + s);
            }

            @Override
            public void onError(Throwable e) {
                log("observer onError");
            }

            @Override
            public void onComplete() {
                log("observer onComplete");
            }
        };
    }

    public void create3() {
        log("create3");
        Observable.create(getIntObservable()).subscribe(new Observer<Integer>() {
            public int i;
            public Disposable mDis;

            @Override
            public void onSubscribe(Disposable d) {
                log("Observer onSubscribe");
                mDis = d;
            }

            @Override
            public void onNext(Integer integer) {
                log("Observer onNext value = " + integer);
                i++;
                if (i == 3) {
                    log("dispose");
                    /**虽然这里调用了dispose但是上游还是会发送数据过来。只要上游没有调用complete，
                     * 这里还是可以收到数据*/
                    mDis.dispose();
                    log("mDis isDisposed = " + mDis.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                log("Observer onError");
            }

            @Override
            public void onComplete() {
                log("Observer onComplete");
            }
        });
    }

    public void create4() {
        log("create4");
        Observable.create(getIntObservable()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                logThreadName("Consumer accept");
                log("Consumer accept value = " + integer);
            }
        });
    }

    @NonNull
    private ObservableOnSubscribe<Integer> getIntObservable() {
        return new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                logThreadName("ObservableEmitter subscribe");
                log("Observable subscribe");
                log("emitter 1");
                e.onNext(1);
                log("emitter 2");
                e.onNext(2);
                log("emitter 3");
                e.onNext(3);
                log("emitter 4");
                e.onNext(4);
                log("complete");
                e.onComplete();
                log("emitter 5");
                e.onNext(5);
            }
        };
    }

    private void logThreadName(String str) {
        log(str + " , Thread id = " + Thread.currentThread().getName());
    }

    /**
     * 线程调度
     * subscribeOn() 指定的是上游发送事件的线程
     * observeOn() 指定的是下游接收事件的线程.
     * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
     * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
     *
     * @param reSchedule 是否多次调用
     */
    public void schedule(boolean reSchedule) {
        log("schedule");
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                logThreadName("ObservableEmitter subscribe");
                log("Observable subscribe");
                log("Emitter 1");
                e.onNext(1);
            }
        });
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                logThreadName("Consumer accept");
                log("Consumer accept value: " + integer);
            }
        };
        if (reSchedule) {
            observable.subscribeOn(Schedulers.newThread())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            log("After observeOn(mainThread), current thread is: " + Thread
                                    .currentThread().getName());
                        }
                    })
                    .observeOn(Schedulers.io())
                    .doOnNext(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            log("After observeOn(io), current thread is : " + Thread
                                    .currentThread().getName());
                        }
                    })
                    .subscribe(consumer);

            /** zytech -> ObservableEmitter subscribe , Thread id = RxNewThreadScheduler-1
             zytech -> Consumer accept , Thread id = RxCachedThreadScheduler-2
             上游虽然指定了两次线程, 但只有第一次指定的有效, 依然是在RxNewThreadScheduler 线程中,
             而下游则跑到了RxCachedThreadScheduler 中, 这个CacheThread其实就是IO线程池中的一个.
             */
            return;
        }

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

}
