package com.swaraj.tada.Fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.swaraj.tada.Interfaces.LoadFragmentCallback;
import com.swaraj.tada.R;
import com.swaraj.tada.TaDaBaseActivity;
import com.swaraj.tada.Tasks;
import com.swaraj.tada.db.TaskContract;
import com.swaraj.tada.db.TaskQueryHandler;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

/**
 * Created by swaraj on 01/11/17
 */

public class FragmentAddTasks extends Fragment {

    EditText text, entryDate, reminderDate, mpriority;
    TextView submit, showTasks;
//    RecyclerView recyclerView;
//    AllTaskAdapter taskAdapter;

    TaskQueryHandler asyncQueryHandler;

    List<Tasks> tasksList;

    LoadFragmentCallback parentActivity;

    Consumer<String > consumer;

    public static FragmentAddTasks getInstance() {
        return new FragmentAddTasks();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_all_tasks, container, false);

        parentActivity = (TaDaBaseActivity) getActivity();
        tasksList = new ArrayList<>();

        text = (EditText) v.findViewById(R.id.task_content);
        entryDate = (EditText) v.findViewById(R.id.task_entry_date);
        reminderDate = (EditText) v.findViewById(R.id.task_reminder_date);
        mpriority = (EditText) v.findViewById(R.id.task_priority);
        submit = (TextView) v.findViewById(R.id.submit);
        showTasks = (TextView) v.findViewById(R.id.show);


        asyncQueryHandler = new TaskQueryHandler(getContext().getContentResolver());

        setClickActions();
        return v;
    }

    private void setClickActions() {
//        setActionForDbOps();
        setActionForRxButtons();
        setActionForRxButtons1();
    }

    private void setActionForRxButtons() {

        //to check if a class level instantiated consumer listen to observable again n again  - it listens
        // I guess we need to unsubscribe the subscription to make it stop listening
        consumer = getConsumer();

        showTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomething();
            }
        });
    }

    private void setActionForRxButtons1() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomethingElse();
            }
        });
    }

    private void setActionForDbOps() {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String content = text.getText().toString();
                final String eDate = entryDate.getText().toString();
                final String rDate = reminderDate.getText().toString();
                final String priority = mpriority.getText().toString();

                addToDb(content, eDate, rDate, priority);
            }
        });
    }

    private void addToDb(String content, String eDate, String rDate, String priority) {
        ContentValues values = new ContentValues();
        values.put(TaskContract.AllTasks.CONTENT, content);
        values.put(TaskContract.AllTasks.ENTRY_TIME, eDate);
        values.put(TaskContract.AllTasks.REMINDER_TIME, rDate);
        values.put(TaskContract.AllTasks.PRIORITY, priority);

        getContext().getContentResolver().insert(TaskContract.AllTasks.CONTENT_URI, values);
    }

    private void doSomething() {
//        getObservable().subscribe(consumer);
//        getObservable().subscribe(getConsumer());
//        getObservable().subscribe(getObserver());

        Observer<String> observer= getObserver();

        Observable.just("Hello Android").subscribe(observer);
        Observable.just("another parallel observable").subscribe(observer);
    }

    private void doSomethingElse() {
        Observable.just("nikki lauda", "james hunt")
                .map(new Function<String, String >() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        return s + " is champ";
                    }
                })
                .subscribe(getObserver());
    }


    private Observable<String> getObservable() {
        Observable<String> myObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                int i = 4 / 0;
                e.onNext(Integer.toString(i));
                e.onComplete();
            }
        });


        return myObservable;
    }

    private Consumer<String> getConsumer(){
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        };

        return con;
    }


    private Observer<String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("rxActions", "observer subscried");
            }

            @Override
            public void onNext(@NonNull String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("rxActions", "error => " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("rxActions", "on complete called");
            }
        };
    }

}
