package com.emebesoft.hideapp.registerView;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.emebesoft.hideapp.application.HideAppApplication;
import com.emebesoft.hideapp.objects.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Juan Lucena on 05/05/2017.
 */

public class RegisterInteractorImpl implements RegisterInteractor{

    private Activity activity;
    private OnRegisterRequested onRegisterRequested;
    private OnLoginRequested onLoginRequested;

    public RegisterInteractorImpl(Activity activity, OnRegisterRequested onRegisterRequested, OnLoginRequested onLoginRequested){
        this.activity = activity;
        this.onRegisterRequested = onRegisterRequested;
        this.onLoginRequested = onLoginRequested;
    }

    @Override
    public void doRegister(final String username, String password) {

        HideAppApplication.mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("LOGIN", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()){
                            User user = new User();
                            user.setUserId(username);
                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("user");
                            mDatabase.child(task.getResult().getUser().getUid()).setValue(user);
                        }

                        onRegisterRequested.registerRequested(task);
                    }
                });
    }

    @Override
    public void doLogin(String username, String password) {
        HideAppApplication.mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        onLoginRequested.loginRequested(task);
                    }
                });
    }
}
