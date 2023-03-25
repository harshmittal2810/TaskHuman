package com.harsh.taskhuman.common.util;

import android.util.Log;

public class AppExceptionHandler extends Throwable {

    private static final String TAG = "XoxoDayExceptionHandler";

    public static void handle(Exception e) {
        Log.e(TAG, "Handling exception to XoxoDayExceptionHandler!", e);
    }
}
