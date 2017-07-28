package com.qonaah.donor.app.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Qona'ah on 11/20/2016.
 */
public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "DonorApp ";
    public static final String KEY_CONTENT_CREATED = "content";

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createContent() {
        editor.putBoolean(KEY_CONTENT_CREATED, true);
        editor.commit();
    }

    public boolean isCreatedContent() {
        return pref.getBoolean(KEY_CONTENT_CREATED, false);
    }


}



