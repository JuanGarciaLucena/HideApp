package com.emebesoft.hideapp.objects;

/**
 * Created by Juan Lucena on 05/05/2017.
 */

public class User {

    private String userId;

    public User(){}

    public User(String userId, String userToken){
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
