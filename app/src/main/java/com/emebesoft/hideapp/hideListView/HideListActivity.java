package com.emebesoft.hideapp.hideListView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.emebesoft.hideapp.R;
import com.emebesoft.hideapp.application.HideAppApplication;
import com.emebesoft.hideapp.hideDetailView.HideDetailActivity;
import com.emebesoft.hideapp.loginView.LoginActivity;
import com.emebesoft.hideapp.objects.Position;
import com.google.firebase.auth.FirebaseAuth;

import org.parceler.Parcels;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HideListActivity extends AppCompatActivity implements HideListView{

    @BindView(R.id.hideRecyclerView)RecyclerView hideRecyclerView;

    private static final String POSITION = "position";
    private HideRecyclerViewAdapter adapter;
    private ProgressDialog progressDialog;
    private HideListPresenter hideListPresenter = new HideListPresenterImpl(this);
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_list);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HideApp");

        hideListPresenter.requestHideListInfo();
    }

    @Override
    public void showProgressDialog(){
        progressDialog = ProgressDialog.show(HideListActivity.this, "Espere, por favor ...", "Conectando ...", true);
        progressDialog.setCancelable(false);
    }

    @Override
    public void hideProgressDialog(){
        progressDialog.dismiss();
    }

    @Override
    public void inflateHideList(final List<Position> positionList) {
        adapter = new HideRecyclerViewAdapter(getApplicationContext(), positionList, new HideRecyclerViewOnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(HideListActivity.this, HideDetailActivity.class);
                intent.putExtra(POSITION, Parcels.wrap(Position.class, positionList.get(position)));
                startActivity(intent);
            }
        });

        hideRecyclerView.setAdapter(adapter);
        hideRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        hideRecyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hide_list_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.menuUser:
                return true;

            case R.id.menuExit:
                HideAppApplication.mAuth.signOut();
                finish();
                startActivity(new Intent(HideListActivity.this, LoginActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            HideAppApplication.mAuth.signOut();
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Para salir pulse otra vez atrás", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}