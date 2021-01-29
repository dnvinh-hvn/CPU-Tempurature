package com.vinhdn.cputemp.data;

import android.content.Context;
import android.content.SharedPreferences;

public class Params {
    public static SharedPreferences reader(Context context) {
        return context.getSharedPreferences(Constants.APP, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor writer(Context context) {
        return reader(context).edit();
    }
}