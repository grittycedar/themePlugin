package com.imesong.themedemo.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by ericsong on 16/3/22.
 */
public class ThemeUtil {
    public static final String THEME_NAME = "ThemeStyle.skin";
    public static final String THEME_PATH = Environment.getExternalStorageState() + File
            .pathSeparator + THEME_NAME;
}
