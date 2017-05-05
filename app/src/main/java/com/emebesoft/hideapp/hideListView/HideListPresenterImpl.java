package com.emebesoft.hideapp.hideListView;

import com.emebesoft.hideapp.objects.Position;

import java.util.List;

/**
 * Created by ayesa1 on 02/05/2017.
 */

public class HideListPresenterImpl implements HideListPresenter, HideListInteractor.OnRequestedHideListInfo{

    private HideListView hideListView;
    private HideListInteractor hideListInteractor = new HideListInteractorImpl(this);

    public HideListPresenterImpl(HideListView hideListView){
        this.hideListView = hideListView;
    }


    @Override
    public void requestHideListInfo() {
        hideListView.showProgressDialog();
        hideListInteractor.doRequestHideListInfo();
    }

    @Override
    public void onRequestedHideListInfo(List<Position> positionList) {
        hideListView.inflateHideList(positionList);
        hideListView.hideProgressDialog();
    }
}
