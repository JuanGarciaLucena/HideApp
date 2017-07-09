package com.emebesoft.hideapp.SplashView;

/**
 * Created by Juan Lucena on 06/05/2017.
 */

public class SplashScreenPresenterImpl implements SplashScreenPresenter, SplashScreenInteractor.OnUserLogedListener{

    private SplashScreenInteractor splashScreenInteractor;
    private SplashScreenView splashScreenView;

    public SplashScreenPresenterImpl(SplashScreenView splashScreenView){
        this.splashScreenView = splashScreenView;
        splashScreenInteractor = new SplashScreenInteractorImpl(this);
    }

    @Override
    public void checkLogedUserRequest() {
        //splashScreenView.showProgress("Espere por favor", "Cargando...");
        splashScreenInteractor.checkLogedUser();
    }

    @Override
    public void userLogedListener(boolean isUserLoged) {
        //splashScreenView.hideProgress();

        if(isUserLoged){
            splashScreenView.goToHideListActivity();
        }else{
            splashScreenView.goToLoginActivity();
        }
    }
}
