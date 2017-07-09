package com.emebesoft.hideapp.reservationView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.emebesoft.hideapp.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReservationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    @BindView(R.id.textViewReservationLabel) TextView textViewReservationLabel;

    private String[] reservedDates;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private DatePickerDialog datePickerDialog;
    private Calendar now = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ButterKnife.bind(ReservationActivity.this);

        //App bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Reserva");

        reservedDates = getIntent().getExtras().getStringArray("reservations");

        datePickerDialog = DatePickerDialog.newInstance(
                ReservationActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        //Listeners
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String dateSelected = "Día seleccionado: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        textViewReservationLabel.setText(dateSelected);
    }

    public void doShowDatePicker(View view){
        datePickerDialog.setDisabledDays(getDaysReserved());
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    public Calendar[] getDaysReserved(){

        Calendar[] calendarArray = new Calendar[reservedDates.length];
        for(int i = 0; i < reservedDates.length; i++){

            try {
                Date date = simpleDateFormat.parse(reservedDates[i]);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendarArray[i] = calendar;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return calendarArray;
    }
}
