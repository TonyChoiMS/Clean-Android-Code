package com.plaps.androidcleancode.home;

import com.plaps.androidcleancode.models.CityListResponse;
import com.plaps.androidcleancode.networking.NetworkError;
import com.plaps.androidcleancode.networking.Service;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ennur on 6/25/16.
 * MVP Pattern의 Prensenter
 */
public class HomePresenter {
    // Custom Network Service
    private final Service service;
    // Interface
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter(Service service, HomeView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCityList() {
        // HomeView의 ShowWait 호출.
        view.showWait();

        // Service를 통해 목표 URL에 접속하여 데이터를 받아옴
        // Success와 Error로 분기
        Subscription subscription = service.getCityList(new Service.GetCityListCallback() {
            @Override
            public void onSuccess(CityListResponse cityListResponse) {
                view.removeWait();
                view.getCityListSuccess(cityListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }
}
