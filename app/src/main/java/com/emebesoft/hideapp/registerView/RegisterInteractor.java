package com.emebesoft.hideapp.registerView;

import com.google.android.gms.tasks.Task;

/**
 * Created by Juan Lucena on 05/05/2017.
 */

public interface RegisterInteractor {

    void doRegister(String username, String password);
    void doLogin(String username, String password);

    interface OnRegisterRequested{
        void registerRequested(Task task);
    }

    interface OnLoginRequested{
        void loginRequested(Task task);
    }
}
