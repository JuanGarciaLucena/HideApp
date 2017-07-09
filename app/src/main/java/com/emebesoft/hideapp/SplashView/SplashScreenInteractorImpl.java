package com.emebesoft.hideapp.SplashView;

import android.content.Intent;

import com.emebesoft.hideapp.application.HideAppApplication;
import com.emebesoft.hideapp.hideListView.HideListActivity;
import com.emebesoft.hideapp.loginView.LoginActivity;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Juan Lucena on 06/05/2017.
 */

public class SplashScreenInteractorImpl implements SplashScreenInteractor{

    private OnUserLogedListener listener;

    public SplashScreenInteractorImpl(OnUserLogedListener listener){
        this.listener = listener;
    }

    @Override
    public void checkLogedUser() {
        FirebaseUser firebaseUser = HideAppApplication.mAuth.getCurrentUser();
        if (firebaseUser != null) {
            listener.userLogedListener(true);
        }else{
            listener.userLogedListener(false);
        }
    }
}
