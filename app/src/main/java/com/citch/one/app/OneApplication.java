package com.citch.one.app;

import android.app.Application;

import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.FirebaseApp;

/*
 * Created by ameh on 16/10/2017.
 */

public class OneApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Firebase app initialization
        FirebaseApp.initializeApp(this);

        // Facebook events logger initialization
        AppEventsLogger.activateApp(this);

        // Firebase local database initialization
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                .setPersistenceEnabled(true)
//                .build();
//        db.setFirestoreSettings(settings);
    }
}
