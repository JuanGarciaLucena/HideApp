package com.emebesoft.hideapp.registerView;

import android.app.Activity;

import com.google.android.gms.tasks.Task;

/**
 * Created by Juan Lucena on 05/05/2017.
 */

public class RegisterPresenterImpl implements RegisterPresenter, RegisterInteractor.OnRegisterRequested, RegisterInteractor.OnLoginRequested{

    private Activity activity;
    private RegisterView registerView;
    RegisterInteractor registerInteractor;

    public RegisterPresenterImpl(Activity activity, RegisterView registerView){
        this.activity = activity;
        this.registerView = registerView;
        registerInteractor = new RegisterInteractorImpl(activity, this, this);
    }

    @Override
    public void doRegisterRequest(String username, String password) {
        registerView.showProgressDialog("Espere, por favor ...", "Registrando nuevo usuario...");
        registerInteractor.doRegister(username, password);
    }

    @Override
    public void registerRequested(Task task) {

        if(task.isSuccessful()){
            registerView.messageOK("Registrado con éxito");
            registerView.hideProgressDialog();
            registerView.doLogin();
        }else{
            registerView.hideProgressDialog();
            registerView.messageFail(task.getException().toString());
        }
    }

    @Override
    public void doLoginRequest(String username, String password) {
        registerView.showProgressDialog("Espere, por favor ...", "Accediendo...");
        registerInteractor.doLogin(username, password);
    }

    @Override
    public void loginRequested(Task task) {
        if(task.isSuccessful()){
            registerView.messageOK("Sesión iniciada con éxito");
            registerView.hideProgressDialog();
            registerView.goToHideListActivity();
        }else{
            registerView.messageFail(task.getException().toString());
            registerView.hideProgressDialog();
        }
    }
}
