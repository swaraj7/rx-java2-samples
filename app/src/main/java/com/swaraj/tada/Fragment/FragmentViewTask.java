package com.swaraj.tada.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.swaraj.tada.Adapter.AllTaskAdapter;
import com.swaraj.tada.R;
import com.swaraj.tada.Tasks;
import com.swaraj.tada.db.TaskContract;
import com.swaraj.tada.db.TaskQueryHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swaraj on 01/11/17
 */

public class FragmentViewTask extends Fragment {

    List<Tasks> tasksList;

    public static FragmentViewTask getInstance() {
        return new FragmentViewTask();
    }

    RecyclerView recyclerView;
    AllTaskAdapter taskAdapter;
    TaskQueryHandler taskQueryHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_view_tasks, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        taskQueryHandler = new TaskQueryHandler(getContext().getContentResolver());

        tasksList = new ArrayList<>();
        updateTaskList();


        taskAdapter = new AllTaskAdapter(getContext(), tasksList);
        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }


    private void updateTaskList() {
        String[] projection = new String[]{
                TaskContract.AllTasks.CONTENT,
                TaskContract.AllTasks.ENTRY_TIME,
                TaskContract.AllTasks.REMINDER_TIME,
                TaskContract.AllTasks.PRIORITY
        };


        taskQueryHandler.startQuery(TaskContract.AllTasks.CONTENT_URI, projection, null,
                null, null, new TaskQueryHandler.QueryCompleteListener() {
                    @Override
                    public void onQueryComplete(int token, Object cookie, Cursor cursor) {
                        if (cursor != null){
                            String data, eTime, rTime, pr;
                            while (cursor.moveToNext()) {
                                data = cursor.getString(cursor.getColumnIndex(TaskContract.AllTasks.CONTENT));
                                eTime = cursor.getString(cursor.getColumnIndex(TaskContract.AllTasks.ENTRY_TIME));
                                rTime = cursor.getString(cursor.getColumnIndex(TaskContract.AllTasks.REMINDER_TIME));
                                pr = cursor.getString(cursor.getColumnIndex(TaskContract.AllTasks.PRIORITY));

                                Tasks task = new Tasks(data, eTime, rTime, pr);
                                tasksList.add(task);

                            }
                            cursor.close();
                            taskAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "cursor is null", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }



}
