package com.test.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.test.rxjavademo.model.ApiUser;
import com.test.rxjavademo.model.User;
import com.test.rxjavademo.utils.Utils;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity {
    @BindView(R.id.simple)
    Button simple;
    @BindView(R.id.map)
    Button map;
    @BindView(R.id.zip)
    Button zip;
    @BindView(R.id.interval)
    Button interval;
    @BindView(R.id.interval2)
    Button interval2;
    @BindView(R.id.singleobserver)
    Button singleobserver;
    @BindView(R.id.completableobserver)
    Button completableobserver;
    @BindView(R.id.filter)
    Button filter;
    @BindView(R.id.scan)
    Button scan;
    @BindView(R.id.buffer)
    Button buffer;
    @BindView(R.id.window)
    Button window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.simple, R.id.map, R.id.zip, R.id.interval, R.id.interval2, R.id.singleobserver, R.id.completableobserver, R.id.filter, R.id.scan, R.id.buffer,R.id.window})
    public void clickAction(View view) {
        switch (view.getId()) {
            case R.id.simple:
                baseUsage();
                break;
            case R.id.map:
                mapUsage();
                break;
            case R.id.zip:
                zipUsage();
                break;
            case R.id.interval:
                intervalUsage();
                break;
            case R.id.interval2:
                disposables.clear();
                break;
            case R.id.singleobserver:
                singleUsage();
                break;
            case R.id.completableobserver:
                compUsage();
                break;
            case R.id.filter:
                filterUsage();
                break;
            case R.id.scan:
                scanUsage();
                break;
            case R.id.buffer:
                bufferUsage();
                break;
            case R.id.window:
                windowUsage();
                break;
            default:
                break;
        }
    }

    public void windowUsage(){
        Observable.interval(1,TimeUnit.SECONDS).take(12)
                .window(3,TimeUnit.SECONDS)
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(Observable<Long> observable) throws Exception {
                        Log.d(MainActivity.TAG, "Sub Divide begin....");
                        observable.subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                Log.d(MainActivity.TAG, "Next:" + aLong);
                            }
                        });
                    }
                });
    }

    private void bufferUsage() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .buffer(3, 1)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.d(MainActivity.TAG, " onNext : size :" + integers.size());
                        for (Integer value : integers) {
                            Log.d(MainActivity.TAG, " : value :" + value);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void scanUsage() {
        Observable.just(1, 2, 3, 4, 5)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer * integer2;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(MainActivity.TAG, "onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void filterUsage() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                        Log.d(MainActivity.TAG, "onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void compUsage() {
        Completable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(MainActivity.TAG, " completable observer. onComplete");
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }
                });
    }

    private void singleUsage() {
        Single.just("Amit")
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        Log.d(MainActivity.TAG, " onSuccess : value : " + s);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }
                });
    }

    CompositeDisposable disposables = new CompositeDisposable();

    private void intervalUsage() {
        disposables.add(Observable.interval(0, 2, TimeUnit.SECONDS)
                .subscribeWith(new DisposableObserver<Long>() {

                    @Override
                    public void onNext(Long aLong) {

                        Log.d(MainActivity.TAG, " onNext : value : " + aLong);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void zipUsage() {
        Observable.zip(getCricketFansObservable(), getFootballFansObservable(), new BiFunction<List<User>, List<User>, List<User>>() {
            @Override
            public List<User> apply(List<User> users, List<User> users2) throws Exception {
                return Utils.filterUserWhoLovesBoth(users, users2);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(List<User> users) {
                        Log.d(MainActivity.TAG, " onNext");
                        for (User user : users) {
                            Log.d(MainActivity.TAG, " onNext : " + user.firstname);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Observable<List<User>> getCricketFansObservable() {
        return Observable.create(new ObservableOnSubscribe<List<User>>() {

            @Override
            public void subscribe(ObservableEmitter<List<User>> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(Utils.getUserListWhoLovesCricket());
                    e.onComplete();
                }
            }
        });
    }

    private Observable<List<User>> getFootballFansObservable() {
        return Observable.create(new ObservableOnSubscribe<List<User>>() {

            @Override
            public void subscribe(ObservableEmitter<List<User>> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(Utils.getUserListWhoLovesFootball());
                    e.onComplete();
                }
            }
        });
    }

    private void mapUsage() {
        Observable.create(new ObservableOnSubscribe<List<ApiUser>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ApiUser>> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext(Utils.getApiUserList());
                    emitter.onComplete();
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<ApiUser>, List<User>>() {
                    @Override
                    public List<User> apply(List<ApiUser> apiUsers) throws Exception {
                        return Utils.convertApiUserListToUserList(apiUsers);
                    }
                })
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(List<User> users) {
                        Log.d(MainActivity.TAG, " onNext");
                        for (User user : users) {
                            Log.d(MainActivity.TAG, " onNext : " + user.firstname);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void baseUsage() {
        Observable.just("hello world", 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Serializable>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        Log.d(MainActivity.TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Serializable serializable) {
                        Log.d(MainActivity.TAG, "onNext " + serializable);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.d(MainActivity.TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(MainActivity.TAG, "onComplete");
                    }
                });
    }
}
