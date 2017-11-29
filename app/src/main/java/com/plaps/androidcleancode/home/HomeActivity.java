package com.plaps.androidcleancode.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.plaps.androidcleancode.BaseApp;
import com.plaps.androidcleancode.R;
import com.plaps.androidcleancode.models.CityListData;
import com.plaps.androidcleancode.models.CityListResponse;
import com.plaps.androidcleancode.networking.Service;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Main Activity
 * MVP Pattern의 View
 * HomeView Interface를 참조하여, 선언된 필요 기능들을 Override함.
 */
public class HomeActivity extends BaseApp implements HomeView {

    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Inject
    public  Service service;

    Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();
        // Presenter 생성 후, 리스트를 불러옴
        // MVP 패턴에서 View는 데이터에 대한 처리를 하지 않기 때문에,
        // Presenter를 통해서 Model에서 데이터를 저장한 후, 불러올 수 있게 동작
        HomePresenter presenter = new HomePresenter(service, this);
        presenter.getCityList();
    }

    public  void renderView(){
        setContentView(R.layout.activity_home);
        unbinder = ButterKnife.bind(this);
    }

    // RecyclerView set LayoutManager
    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Showing ProgressDialog
     */
    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Remove ProgressDialog
     */
    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    /**
     *  Receive App Error Message
     * @param appErrorMessage
     */
    @Override
    public void onFailure(String appErrorMessage) {

    }

    /**
     * When Network Success, Get Response List Data
     * @param cityListResponse  response list about city
     */
    @Override
    public void getCityListSuccess(CityListResponse cityListResponse) {
        // Network를 통해 정보를 성공적으로 불러왔을 경우,
        // 리스트를 불러와서 Adapter에 Data Set.
        HomeAdapter adapter = new HomeAdapter(getApplicationContext(), cityListResponse.getData(),
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(CityListData Item) {
                        Toast.makeText(getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        list.setAdapter(adapter);

    }

    /**
     * When activity destroy, unbind views
     */
    @Override
    protected void onDestroy() {
        unbinder.unbind();      // Activity 종료 시, Bind한 View unBind;
        super.onDestroy();
    }
}
