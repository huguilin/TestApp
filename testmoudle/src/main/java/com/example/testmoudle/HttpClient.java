package com.example.testmoudle;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class HttpClient {

    public HttpClient() {
    }

    public void http(){
        /*
         *  几种创建被观察者的方式：
         */
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        });

        Observable observable1 = Observable.just("1","2");
        String[]  strs = {"a","b","c"};
        Observable<String> observable2 = Observable.fromArray(strs);
        /**
         * 几种创建观察者方式：
         *
         */

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("hgl","onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("hgl",integer+"");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("hgl","onError");
            }

            @Override
            public void onComplete() {
                Log.e("hgl","onComplete");
            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.e("hgl1","onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.e("hgl1",s);
            }

            @Override
            public void onError(Throwable t) {
                Log.e("hgl1","onError");
            }

            @Override
            public void onComplete() {
                Log.e("hgl1","onComplete");
            }
        };


        observable.subscribe(observer);
//        observable.


    }

    public void httpTo(){
        Observable.create(new ObservableOnSubscribe<String>(){

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private String i = "1";
    public void http1(){
        Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                return Observable.just(i);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void http2(){
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void http3(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer+"---";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void http4(){
        Observable.just(1,2,3,4,5)
                .buffer(3,1)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });




    }

}
