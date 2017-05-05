package com.emebesoft.hideapp.application;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ayesa1 on 28/04/2017.
 */

public class HideAppApplication  extends Application {

    public static FirebaseAuth mAuth;

    @Override
    public void onCreate() {
        super.onCreate();
        mAuth = FirebaseAuth.getInstance();
    }
}
