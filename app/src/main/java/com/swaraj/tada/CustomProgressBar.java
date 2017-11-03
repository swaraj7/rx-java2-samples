package com.swaraj.tada;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Window;
import android.view.animation.CycleInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by swaraj on 02/11/17
 */

public class CustomProgressBar extends Dialog {
    public CustomProgressBar(@NonNull Context context) {
        super(context);
    }

    public CustomProgressBar(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected CustomProgressBar(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    ProgressBar loader;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_progress_bar);

        loader = (ProgressBar) findViewById(R.id.circular_loader);
        message = (TextView) findViewById(R.id.message);
    }
}
