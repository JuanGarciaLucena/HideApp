package com.emebesoft.hideapp.registerView;

/**
 * Created by Juan Lucena on 05/05/2017.
 */

public interface RegisterView {

    void showProgressDialog(String title, String message);
    void hideProgressDialog();
    void messageOK(String message);
    void messageFail(String exception);
    void doLogin();
    void goToHideListActivity();
}
