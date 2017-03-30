package com.shahzeb.sc.utils;

import android.app.Activity;
import android.view.WindowManager;

public class SCUtil {

    public static void hideSoftKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
