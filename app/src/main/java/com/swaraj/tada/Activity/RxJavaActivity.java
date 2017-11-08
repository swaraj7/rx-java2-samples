package com.swaraj.tada.Activity;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.swaraj.tada.R;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.internal.disposables.DisposableContainer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    TextView disposableBtn;
    TextView displayArea;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        disposableBtn = (TextView) findViewById(R.id.disposable);
        displayArea = (TextView) findViewById(R.id.displayArea);

        setActions();
    }

    private void setActions() {
        disposableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDisposableWork();
            }
        });
    }

    private void doDisposableWork() {
        disposable.add(getObservable().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DisposableObserver<String >() {

                    @Override
                    public void onNext(@NonNull String s) {
                        displayArea.setText(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("rxjava", e.toString());
                    }

                    @Override
                    public void onComplete() {
                        displayArea.append(" Complete ");
                        Log.d("rxjava", "complete");
                    }
                }));
    }

    private Observable<String> getObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
//                SystemClock.sleep(2000);
                return Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
