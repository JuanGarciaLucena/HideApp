package com.emebesoft.hideapp.objects;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by ayesa1 on 03/05/2017.
 */

@Parcel
public class PositionReservation {

    private String reservationDate;

    public PositionReservation(){}

    public PositionReservation(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}
