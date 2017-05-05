package com.emebesoft.hideapp.hideListView;

import com.emebesoft.hideapp.objects.Position;

import java.util.List;

/**
 * Created by ayesa1 on 02/05/2017.
 */

public interface HideListInteractor {

    void doRequestHideListInfo();

    interface OnRequestedHideListInfo{
        void onRequestedHideListInfo(List<Position> positionList);
    }
}
