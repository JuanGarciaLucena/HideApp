package com.emebesoft.hideapp.SplashView;

/**
 * Created by Juan Lucena on 06/05/2017.
 */

public interface SplashScreenInteractor {
    void checkLogedUser();

    interface OnUserLogedListener{
        void userLogedListener(boolean isUserLoged);
    }
}
