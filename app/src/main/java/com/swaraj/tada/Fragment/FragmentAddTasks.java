package com.swaraj.tada.Fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.swaraj.tada.Interfaces.LoadFragmentCallback;
import com.swaraj.tada.R;
import com.swaraj.tada.TaDaBaseActivity;
import com.swaraj.tada.Tasks;
import com.swaraj.tada.db.TaskContract;
import com.swaraj.tada.db.TaskQueryHandler;

import java.util.ArrayList;
import java.util.List;

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

    public static FragmentAddTasks getInstance() {
        return new FragmentAddTasks();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_all_tasks, container, false);

        parentActivity = (TaDaBaseActivity) getActivity();
        tasksList = new ArrayList<>();
//        taskAdapter = new AllTaskAdapter(getContext(), tasksList);


        text = (EditText) v.findViewById(R.id.task_content);
        entryDate = (EditText) v.findViewById(R.id.task_entry_date);
        reminderDate = (EditText) v.findViewById(R.id.task_reminder_date);
        mpriority = (EditText) v.findViewById(R.id.task_priority);
//        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        submit = (TextView) v.findViewById(R.id.submit);
        showTasks = (TextView) v.findViewById(R.id.show);


//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(taskAdapter);
//        recyclerView.setVisibility(View.GONE);

        asyncQueryHandler = new TaskQueryHandler(getContext().getContentResolver());

        setClickActions();

        showTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.loadFragment(null, 1, "allTask");
            }
        });
        return v;
    }

    private void setClickActions() {
        setActionForDbOps();

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

//        updateTaskList();
    }

//


}
