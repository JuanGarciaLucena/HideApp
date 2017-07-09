package com.emebesoft.hideapp.userView;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.emebesoft.hideapp.R;
import com.emebesoft.hideapp.application.HideAppApplication;
import com.emebesoft.hideapp.loginView.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {

    @BindView(R.id.appbar) Toolbar appbar;
    @BindView(R.id.textViewAppName) TextView textViewAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(UserActivity.this);

        setSupportActionBar(appbar);
        getSupportActionBar().setTitle("Panel de usuario");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Typeface appTitletypeFace = Typeface.createFromAsset(getAssets(), "fonts/title_font.ttf");
        textViewAppName.setTypeface(appTitletypeFace);

        //Listeners
        appbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void doSendNewPasswordMail (View view){

        String emailAddress = HideAppApplication.mAuth.getCurrentUser().getEmail();

        HideAppApplication.mAuth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("LOGIN", "Email sent.");
                        }else{
                            Log.e("LOGIN", task.getException().toString());
                        }
                    }
                });
    }

    public void doDeleteUser(View view){

        HideAppApplication.mAuth.getCurrentUser().delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("LOGIN", "User account deleted.");
                            startActivity(new Intent(UserActivity.this, LoginActivity.class));
                        }else{
                            Log.e("LOGIN", task.getException().toString());
                        }
                    }
                });
    }
}
