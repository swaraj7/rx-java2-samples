package com.swaraj.tada.Interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by swaraj on 01/11/17
 */

public interface LoadFragmentCallback {
    public void loadFragment(Bundle bundle, int fragmentId, String tag);
}
