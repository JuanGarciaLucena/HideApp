package com.emebesoft.hideapp.SplashView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.emebesoft.hideapp.R;
import com.emebesoft.hideapp.hideListView.HideListActivity;
import com.emebesoft.hideapp.loginView.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenView {

    @BindView(R.id.textViewAppName) TextView textViewAppName;
    @BindView(R.id.textViewEmebesoftLogo) TextView textViewEmebesoftLogo;

    private ProgressDialog progressDialog;
    private SplashScreenPresenter splashScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splas_screen);
        ButterKnife.bind(SplashScreenActivity.this);

        splashScreenPresenter = new SplashScreenPresenterImpl(SplashScreenActivity.this);

        Typeface appTitletypeFace = Typeface.createFromAsset(getAssets(), "fonts/title_font.ttf");
        textViewAppName.setTypeface(appTitletypeFace);

        Typeface emebesotfTypeFace = Typeface.createFromAsset(getAssets(), "fonts/PressStart2P-Regular.ttf");
        textViewEmebesoftLogo.setTypeface(emebesotfTypeFace);


        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally {
                    splashScreenPresenter.checkLogedUserRequest();
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void goToLoginActivity() {
        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void goToHideListActivity() {
        startActivity(new Intent(SplashScreenActivity.this, HideListActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}