package com.qonaah.donor.app.service;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.qonaah.donor.app.helper.ContenHelper;
import com.qonaah.donor.app.helper.TypefaceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Qona'ah on 09/08/16.
 */

public class MainApplication extends Application {

    public static final String IMAGE_PATH = createResourceFolder("/doa/image/");
    public static final String VIDEO_PATH = createResourceFolder("/doa/video/");
    public static final String AUDIO_PATH = createResourceFolder("/doa/audio/");

    private SessionManager sessionManager;

    @Override
    public void onCreate() {
        super.onCreate();
        initFile();

        // Jika pertama kali di install aplikasi akan menyimpan shares preference agak cuma sekali insert contebt
        // default
        if (!sessionManager.isCreatedContent()){
            sessionManager.createContent();
            ContenHelper.getInstance(this).insertContent();


            TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "Sansation-Light.otf"); // font from assets: "assets/fonts/Sansation-Light.otf
        }
        }

    static String createResourceFolder(String path) {
        if (Environment.isExternalStorageEmulated() || Environment.getExternalStorageDirectory() != null) {
            return Environment.getExternalStorageDirectory().getPath().concat(path);
        } else {
            return Environment.getDataDirectory().getPath().concat(path);
        }
    }

    public static String convertDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.US);
        return dateFormat.format(date);
    }

    /**
     * CREATE FOLDER
     */
    private void initFile() {
        final File imageFolder = new File(MainApplication.IMAGE_PATH);
        if (!imageFolder.exists()) {
            imageFolder.mkdirs();
            Log.d("FOLDER", "IMAGE_PATH folder created!");
        } else {
            Log.d("FOLDER", "folder exist!");
        }

        final File videoFolder = new File(MainApplication.VIDEO_PATH);
        if (!videoFolder.exists()) {
            videoFolder.mkdir();
            Log.d("FOLDER", "VIDEO_PATH folder created!");
        } else {
            Log.d("FOLDER", "folder exist!");
        }

        final File audioFolder = new File(MainApplication.AUDIO_PATH);
        if (!audioFolder.exists()) {
            audioFolder.mkdir();
            Log.d("FOLDER", "AUDIO_PATH folder created!");
        } else {
            Log.d("FOLDER", "folder exist!");
        }
    }

    public static void copyFile(File fileSrc, File fileDest) {
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(fileSrc);
            outStream = new FileOutputStream(fileDest);

            FileChannel inChannel = inStream.getChannel();
            FileChannel outChannel = outStream.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inStream != null && outStream != null) {
                try {
                    inStream.close();
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
