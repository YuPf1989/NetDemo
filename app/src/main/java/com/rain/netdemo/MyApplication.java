package com.rain.netdemo;

import android.app.Application;
import android.util.Log;

/**
 * Author:rain
 * Date:2017/11/13 15:26
 * Description:
 *
 *
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("rrrr", "onCreate: myApplication");
        myApplication = this;
    }

    public static MyApplication getInstance() {
        return myApplication;
    }
}
