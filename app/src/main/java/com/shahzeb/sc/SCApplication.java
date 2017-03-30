package com.shahzeb.sc;

import android.app.Application;
import butterknife.ButterKnife;

public class SCApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ButterKnife.setDebug(true);
    }

}
