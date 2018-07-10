[资料](https://www.jianshu.com/p/8818b98c44e2)

ObservableEmitter是Emitter发射器，用来发送事件，通过调用Emitter的onNext(),onComplete,onError可以分别发出next,complete,error事件

Disposable一次性的用完即可丢弃的
  调用Dispose并不会导致上游不再继续发送事件，上游会继续发送剩余的事件

ObservableEmitter subscribe , Thread id = main   
Consumer accept , Thread id = main
当我们在主线程中去创建一个上游Observable来发送事件, 则这个上游默认就在主线程发送事件.
当我们在主线程去创建一个下游Observer来接收事件, 则这个下游默认就在主线程中接收事件
上下游默认是在同一个线程工作.

## 线程调度 ##

- subscribeOn() 指定的是上游发送事件的线程
- observeOn() 指定的是下游接收事件的线程.
	
		多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
	
		多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.


在RxJava中, 已经内置了很多线程选项供我们选择, 例如有

- Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
- Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
- Schedulers.newThread() 代表一个常规的新线程
- AndroidSchedulers.mainThread() 代表Android的主线程



