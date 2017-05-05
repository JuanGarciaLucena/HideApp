package com.emebesoft.hideapp.objects;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Parcel
public class Position {

    private String positionName;
    private String positionPicture;
    private String positionPrice;
    private double positionLatitude;
    private double positionLongitude;
    private String positionDescription;
    private List<String> positionReservation;

    public Position(){}

    public Position(String positionName, String positionPicture, String positionPrice, double positionLatitude, double positionLongitude, List<String> positionReservation){
        this.positionName = positionName;
        this.positionPicture = positionPicture;
        this.positionPrice = positionPrice;
        this.positionLatitude = positionLatitude;
        this.positionLongitude = positionLongitude;
        this.positionReservation = positionReservation;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionPicture() {
        return positionPicture;
    }

    public void setPositionPicture(String positionPicture) {
        this.positionPicture = positionPicture;
    }

    public String getPositionPrice() {
        return positionPrice;
    }

    public void setPositionPrice(String positionPrice) {
        this.positionPrice = positionPrice;
    }

    public double getPositionLatitude() {
        return positionLatitude;
    }

    public void setPositionLatitude(double positionLatitude) {
        this.positionLatitude = positionLatitude;
    }

    public double getPositionLongitude() {
        return positionLongitude;
    }

    public void setPositionLongitude(double positionLongitude) {
        this.positionLongitude = positionLongitude;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    public List<String> getPositionReservation() {
        return positionReservation;
    }

    public void setPositionReservation(List<String> positionReservation) {
        this.positionReservation = positionReservation;
    }
}