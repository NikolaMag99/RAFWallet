package raf.rs.projekat1.aplication;

import android.app.Application;

import timber.log.Timber;

public class Projeka1Application extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }

}
