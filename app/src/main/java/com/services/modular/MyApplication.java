package com.services.modular;

import android.app.Application;
import android.content.Context;

/**
 * Created by lewishstart on 2018/5/8.
 */

public class MyApplication extends Application {
    private static Context mContext;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
    }

    public static MyApplication getInstance(){
        return instance;
    }
}
