package com.swaraj.tada.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.swaraj.tada.R;
import com.swaraj.tada.Tasks;

import java.util.List;

/**
 * Created by swaraj on 02/11/17
 */

public class AllTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    List<Tasks> taskList;

    public AllTaskAdapter(Context context, List<Tasks> taskList) {
        this.mContext = context;
        this.taskList = taskList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_row_task, parent, false);
        return new AllTasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AllTasksViewHolder mHolder = (AllTasksViewHolder) holder;
        Tasks task = taskList.get(position);
        mHolder.data.setText(task.getData());
        mHolder.sTime.setText(task.getsTime());
        mHolder.eTime.setText(task.geteTime());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class AllTasksViewHolder extends RecyclerView.ViewHolder {

        private TextView data;
        private TextView sTime, eTime;

        public AllTasksViewHolder(View itemView) {
            super(itemView);
            data = (TextView) itemView.findViewById(R.id.content);
            sTime = (TextView) itemView.findViewById(R.id.sTime);
            eTime = (TextView) itemView.findViewById(R.id.eTime);
        }
    }
}
