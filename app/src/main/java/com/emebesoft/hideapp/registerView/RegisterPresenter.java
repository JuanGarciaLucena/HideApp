package com.emebesoft.hideapp.registerView;

/**
 * Created by Juan Lucena on 05/05/2017.
 */

public interface RegisterPresenter {

    void doRegisterRequest(String username, String password);
    void doLoginRequest(String username, String password);
}
