package com.dubr0vin.onlineshooter;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("start onCreate");
    }
}
