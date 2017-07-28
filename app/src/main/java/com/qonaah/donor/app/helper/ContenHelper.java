package com.qonaah.donor.app.helper;

import android.content.Context;
import android.util.Log;

/**
 * Created by Qona'ah on 7/26/2017.
 */

public class ContenHelper {

    private static ContenHelper sInstance;

    public static synchronized ContenHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ContenHelper();

        }

        return sInstance;
    }

    public void insertContent() {

        Log.d("ContenHelper", "INSERT CONTENT SUCCESS");
    }

}