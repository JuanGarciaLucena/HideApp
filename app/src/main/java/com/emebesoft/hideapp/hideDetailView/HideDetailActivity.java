package com.emebesoft.hideapp.hideDetailView;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.emebesoft.hideapp.R;
import com.emebesoft.hideapp.objects.Position;
import com.emebesoft.hideapp.reservationView.ReservationActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HideDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.textViewPositionPrice) TextView textViewPositionPrice;
    @BindView(R.id.textViewDescription) TextView textViewDescription;
    @BindView(R.id.imgToolbar) ImageView imgToolbar;
    @BindView(R.id.appbar) Toolbar appbar;

    private static final String POSITION = "position";
    public static GoogleApiClient GOOGLE_API_CLIENT;
    private GoogleMap googleMap;
    private Position position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_detail);
        ButterKnife.bind(this);

        position = Parcels.unwrap(getIntent().getParcelableExtra(POSITION));
        textViewPositionPrice.setText(position.getPositionPrice());
        textViewDescription.setText(position.getPositionDescription());
        Glide.with(HideDetailActivity.this)
                .load(position.getPositionPicture())
                .placeholder(R.drawable.pic_hide)
                .into(imgToolbar);

        //App bar
        setSupportActionBar(appbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Puesto " + position.getPositionName());

        //Floating Action Button
        FloatingActionButton btnFab = (FloatingActionButton)findViewById(R.id.btnFab);
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> reservedDates = new ArrayList<>();
                for(int i = 0; i < position.getPositionReservation().toArray().length; i++){
                    reservedDates.add((String)position.getPositionReservation().toArray()[i]);
                }

                String[] reservedArrayDates = new String[reservedDates.size()];
                reservedDates.toArray(reservedArrayDates);

                Intent reservationIntent = new Intent(HideDetailActivity.this, ReservationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArray("reservations", reservedArrayDates);
                reservationIntent.putExtras(bundle);
                startActivity(reservationIntent);

            }
        });

        //Listeners
        appbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Localización: la clase debe implementar LocationCallback
        /*GOOGLE_API_CLIENT = null;
        GOOGLE_API_CLIENT = new GoogleApiClient.Builder(HideDetailActivity.this)
                .addConnectionCallbacks(new LocationService(this,this))
                .addOnConnectionFailedListener(new LocationService(this,this))
                .addApi(LocationServices.API)
                .build();
        GOOGLE_API_CLIENT.connect();*/


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        this.googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(position.getPositionLatitude(), position.getPositionLongitude()))
                .title("Puesto " + position.getPositionName()));

        LatLngBounds SEVILLA = new LatLngBounds(new LatLng(position.getPositionLatitude(), position.getPositionLongitude()), new LatLng(position.getPositionLatitude(), position.getPositionLongitude()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEVILLA.getCenter(), 15));
    }

    /*@Override
    public void onLocated(Location location) {
        GOOGLE_API_CLIENT.disconnect();
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                .title("Hello world"));

        LatLngBounds SEVILLA = new LatLngBounds(new LatLng(location.getLatitude(), location.getLongitude()), new LatLng(location.getLatitude(), location.getLongitude()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEVILLA.getCenter(), 9));
    }*/
}