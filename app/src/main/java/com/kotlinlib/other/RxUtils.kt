package com.kotlinlib.other

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask
import java.util.function.Consumer


interface RxUtils<T> {

    /**
     * 创建被观察者
     * @return Observable<Int>
     */
    fun createObservable(event:(e: ObservableEmitter<T>)->Unit): Observable<T> {
        return Observable.create { e ->
            event.invoke(e)
        }
    }

    /**
     * 创建观察者
     * @return Observer<Int>
     */
    fun createObserver(next:(t:T)->Unit,complete:()->Unit, subscribe:(d: Disposable)->Unit,
                       error:(e:Throwable)->Unit): Observer<T> {
        return object : Observer<T> {

            override fun onNext(t: T) {
                next.invoke(t)
            }

            override fun onSubscribe(d: Disposable) {
                subscribe.invoke(d)
            }

            override fun onError(e: Throwable) {
                error.invoke(e)
            }

            override fun onComplete() {
                complete.invoke()
            }

        }
    }

    /**
     * 创建一个被观察者，并发送事件，发送的事件不可以超过10个以上
     */
    fun just(vararg t:T):Observable<T>{
        val list = t.toList()
        return when(list.size){
            1->{
                Observable.just(list[0])
            }
            2->{
                Observable.just(list[0],list[1])
            }
            3->{
                Observable.just(list[0],list[1],list[2])
            }
            4->{
                Observable.just(list[0],list[1],list[2],list[3])
            }
            5->{
                Observable.just(list[0],list[1],list[2],list[3],list[4])
            }
            6->{
                Observable.just(list[0],list[1],list[2],list[3],list[4],list[5])
            }
            7->{
                Observable.just(list[0],list[1],list[2],list[3],list[4],list[5],list[6])
            }
            8->{
                Observable.just(list[0],list[1],list[2],list[3],list[4],list[5],list[6],list[7])
            }
            9->{
                Observable.just(list[0],list[1],list[2],list[3],list[4],list[5],list[6],list[7],list[8])
            }
            else->{
                Observable.just(list[0],list[1],list[2],list[3],list[4],list[5],list[6],list[7],list[8],list[9])
            }
        }
    }

    /**
     * 基本等同于just，但不限数量
     */
    fun fromArray(vararg t:T): Observable<T> {
        return Observable.fromArray(*t)
    }

    /**
     * 这里的 Callable 是 java.util.concurrent 中的 Callable，
     * Callable 和 Runnable 的用法基本一致，只是它会返回一个结果值，这个结果值就是发给观察者的
     */
    fun fromCallable(t:T): Observable<T> {
        return Observable.fromCallable { t }
    }



    /**
     * 建立一对一的订阅关系
     */
    fun subscribe(){
        createObservable {

        }.subscribe(createObserver({

        },{

        },{

        },{

        }))
    }

}


