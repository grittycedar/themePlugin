package com.imesong.themedemo;

import com.imesong.themeplugin.loader.SkinManager;

import android.app.Application;

/**
 * Created by ericsong on 16/3/22.
 */
public class MyApplicaiton extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SkinManager.getInstance().init(this);
        SkinManager.getInstance().load();
    }
}
