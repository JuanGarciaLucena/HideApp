package com.emebesoft.hideapp.hideListView;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;

import com.emebesoft.hideapp.hideDetailView.HideDetailActivity;
import com.emebesoft.hideapp.objects.Position;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by ayesa1 on 02/05/2017.
 */

public class HideListInteractorImpl implements HideListInteractor{

    private OnRequestedHideListInfo listener;

    public HideListInteractorImpl(OnRequestedHideListInfo listener){
        this.listener = listener;
    }

    @Override
    public void doRequestHideListInfo() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("positions");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Position>> genericTypeIndicator = new GenericTypeIndicator<List<Position>>() {};
                listener.onRequestedHideListInfo(dataSnapshot.getValue(genericTypeIndicator));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ERROR", "Error!", databaseError.toException());
            }
        });
    }
}
