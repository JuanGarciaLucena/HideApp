package com.emebesoft.hideapp.loginView;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.emebesoft.hideapp.R;
import com.emebesoft.hideapp.application.HideAppApplication;
import com.emebesoft.hideapp.hideListView.HideListActivity;
import com.emebesoft.hideapp.registerView.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.textViewAppName) TextView textViewAppName;
    @BindView(R.id.editTextUsername) EditText editTextUsername;
    @BindView(R.id.editTextPassword) EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(LoginActivity.this);

        Typeface appTitletypeFace = Typeface.createFromAsset(getAssets(), "fonts/title_font.ttf");
        textViewAppName.setTypeface(appTitletypeFace);

        if (HideAppApplication.mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(LoginActivity.this, HideListActivity.class));
        }
    }

    public void doLogin(View view){
        if(validateLogin()) {
            HideAppApplication.mAuth.signInWithEmailAndPassword(editTextUsername.getText().toString(), editTextPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "FAIL :(", Toast.LENGTH_SHORT).show();
                                Log.w("LOGIN", "signInWithEmail:failed", task.getException());
                            }else{
                                Toast.makeText(LoginActivity.this, "SUCCESS :D", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(LoginActivity.this, HideListActivity.class));
                            }
                        }
                    });
        }
    }

    private boolean validateLogin(){

        if(editTextUsername.getText().toString().isEmpty()){
            editTextUsername.setError("Dirección de correo requerida");
            return false;
        }

        if(editTextPassword.getText().toString().isEmpty()){
            editTextPassword.setError("Introduzca su contraseña");
            return false;
        }

        return true;
    }

    public void doRegister(View view){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void doSendPasswordMail(View view){

    }
}