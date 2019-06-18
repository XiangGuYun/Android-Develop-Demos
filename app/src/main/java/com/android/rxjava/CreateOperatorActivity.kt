package com.android.rxjava

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_create_operator.*

@LayoutId(R.layout.activity_create_operator)
class CreateOperatorActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {

        header1.setLeftClick {
            codeDialog.text("""
create（）

作用
完整创建1个被观察者对象（Observable）


RxJava 中创建被观察者对象最基本的操作符


具体使用

/ **
   * 1. 通过creat（）创建被观察者 Observable 对象
   */
Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
// 传入参数： OnSubscribe 对象
// 当 Observable 被订阅时，OnSubscribe 的 call() 方法会自动被调用，即事件序列就会依照设定依次被触发
// 即观察者会依次调用对应事件的复写方法从而响应事件
// 从而实现由被观察者向观察者的事件传递 & 被观察者调用了观察者的回调方法 ，即观察者模式
/ **
   * 2. 在复写的subscribe（）里定义需要发送的事件
   */
@Override
public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
    // 通过 ObservableEmitter类对象 产生 & 发送事件
    // ObservableEmitter类介绍
        // a. 定义：事件发射器
        // b. 作用：定义需要发送的事件 & 向观察者发送事件
       // 注：建议发送事件前检查观察者的isUnsubscribed状态，以便在没有观察者时，让Observable停止发射数据
        if (!observer.isUnsubscribed()) {
               emitter.onNext(1);
               emitter.onNext(2);
               emitter.onNext(3);
    }
    emitter.onComplete();
}
});

// 至此，一个完整的被观察者对象（Observable）就创建完毕了。


在具体使用时，一般采用 链式调用 来创建
    // 1. 通过creat（）创建被观察者对象
Observable.create(new ObservableOnSubscribe<Integer>() {

    // 2. 在复写的subscribe（）里定义需要发送的事件
    @Override
    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);

        emitter.onComplete();
    }  // 至此，一个被观察者对象（Observable）就创建完毕
}).subscribe(new Observer<Integer>() {
    // 以下步骤仅为展示一个完整demo，可以忽略
    // 3. 通过通过订阅（subscribe）连接观察者和被观察者
    // 4. 创建观察者 & 定义响应事件的行为
    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "开始采用subscribe连接");
    }
    // 默认最先调用复写的 onSubscribe（）

    @Override
    public void onNext(Integer value) {
        Log.d(TAG, "接收到了事件"+ value  );
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "对Error事件作出响应");
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "对Complete事件作出响应");
    }

});
            """.trimIndent())
        }

    }

}
