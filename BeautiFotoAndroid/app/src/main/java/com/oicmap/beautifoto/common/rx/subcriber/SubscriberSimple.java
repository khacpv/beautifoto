package com.oicmap.beautifoto.common.rx.subcriber;

import rx.Subscriber;

/**
 * Created by khacpham on 7/4/15.
 */
public class SubscriberSimple<T> extends Subscriber<T> {
    private T t = null;
    private Throwable e = null;

    @Override
    public void onCompleted() {
        onCompleted(this.e == null && t != null, this.t, this.e);
    }

    /**
     * @param t object result<br/>
     * @param e error result
     * */
    public void onCompleted(boolean success, T t, Throwable e){

    }

    @Override
    public void onError(Throwable e) {
        this.e  = e;
        onCompleted(false,null,e);
    }

    @Override
    public void onNext(T t) {
        this.t = t;
    }
}
