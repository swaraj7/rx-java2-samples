package com.swaraj.tada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.swaraj.tada.Fragment.FragmentAddTasks;
import com.swaraj.tada.Fragment.FragmentViewTask;
import com.swaraj.tada.Interfaces.LoadFragmentCallback;

public class TaDaBaseActivity extends AppCompatActivity implements LoadFragmentCallback{
    CustomProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ta_da_base);
        loader = new CustomProgressBar(TaDaBaseActivity.this);
        loader.dismiss();

        loadAddTaskFragment();
    }

    private void loadAddTaskFragment() {
        FragmentAddTasks fragmentAddTasks = FragmentAddTasks.getInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentAddTasks).commit();
    }

    @Override
    public void loadFragment(Bundle bundle, int fragmentId, String tag) {
//        loader.show();
        if(fragmentId == 1) {
            FragmentViewTask fragmentViewTask = FragmentViewTask.getInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentViewTask).commit();
        }

    }
}
