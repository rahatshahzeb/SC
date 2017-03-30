package com.shahzeb.sc;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

public class SCApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        ButterKnife.setDebug(true);
    }

    public static Context getContext(){
        return mContext; }

}
