package com.emebesoft.hideapp.registerView;

import android.app.ProgressDialog;
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
import com.emebesoft.hideapp.objects.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements RegisterView{

    @BindView(R.id.textViewAppName) TextView textViewAppName;
    @BindView(R.id.editTextUsername) EditText editTextUsername;
    @BindView(R.id.editTextUsernameConfirm) EditText editTextUsernameConfirm;
    @BindView(R.id.editTextPassword) EditText editTextPassword;
    @BindView(R.id.editTextPasswordConfirm) EditText editTextPasswordConfirm;

    RegisterPresenter registerPresenter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(RegisterActivity.this);

        registerPresenter = new RegisterPresenterImpl(RegisterActivity.this, RegisterActivity.this);

        Typeface appTitletypeFace = Typeface.createFromAsset(getAssets(), "fonts/title_font.ttf");
        textViewAppName.setTypeface(appTitletypeFace);
    }

    public void doRegister(View view){
        if(validateForm()) {
            registerPresenter.doRegisterRequest(editTextUsername.getText().toString(), editTextPassword.getText().toString());
        }
    }

    @Override
    public void doLogin() {
        registerPresenter.doLoginRequest(editTextUsername.getText().toString(), editTextPassword.getText().toString());
    }

    @Override
    public void showProgressDialog(String title, String message) {
        progressDialog = ProgressDialog.show(RegisterActivity.this, title, message, true);
        progressDialog.setCancelable(false);
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void messageOK(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void messageFail(String exception) {
        Toast.makeText(RegisterActivity.this, "Error: " + exception, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToHideListActivity() {
        startActivity(new Intent(RegisterActivity.this, HideListActivity.class));
        finish();
    }

    public boolean validateForm(){

        String username = editTextUsername.getText().toString();
        String userNameConfirm = editTextUsernameConfirm.getText().toString();
        String password = editTextPassword.getText().toString();
        String passwordConfirm = editTextPasswordConfirm.getText().toString();

        if(username.isEmpty() || userNameConfirm.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()){

            if(username.isEmpty()){
                editTextUsername.setError("Campo vacío");
            }

            if(userNameConfirm.isEmpty()){
                editTextUsernameConfirm.setError("Campo vacío");
            }

            if(password.isEmpty()){
                editTextPassword.setError("Campo vacío");
            }

            if(passwordConfirm.isEmpty()){
                editTextPasswordConfirm.setError("Campo vacío");
            }

            return false;
        }

        if(!username.equals(userNameConfirm)){
            editTextUsername.setError("Las direcciones de correo no coinciden");
            editTextUsernameConfirm.setError("Las direcciones de correo no coinciden");
        }

        if(!password.equals(passwordConfirm)){
            editTextPassword.setError("Las contraseñas no coinciden");
            editTextPasswordConfirm.setError("Las contraseñas no coinciden");
        }

        if(username.equals(userNameConfirm) && password.equals(passwordConfirm)){
            return true;
        }

        return false;
    }


}
