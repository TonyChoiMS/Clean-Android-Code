package com.plaps.androidcleancode.home;

import com.plaps.androidcleancode.models.CityListResponse;

/**
 * Created by ennur on 6/25/16.
 * Main Activity에서 사용할 기능들을 선언하는 Interface
 * View(HomeActivity)와 Presenter(HomePresenter) 사이의 데이터 전달을 위해 사용.
 */
public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCityListSuccess(CityListResponse cityListResponse);

}
