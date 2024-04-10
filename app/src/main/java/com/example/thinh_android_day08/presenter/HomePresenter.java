package com.example.thinh_android_day08.presenter;

import com.example.thinh_android_day08.interactors.HomeInteractor;
import com.example.thinh_android_day08.interfaces.IHomePresenter;
import com.example.thinh_android_day08.interfaces.IHomeView;
import com.example.thinh_android_day08.objects.AllProductResponse;

public class HomePresenter implements IHomePresenter {
    private IHomeView mHomeView;
    private HomeInteractor mHomeInteractor;
    public HomePresenter(IHomeView iHomeView){
        this.mHomeView = iHomeView;
        mHomeInteractor = new HomeInteractor(this);
    }

    public void getAllProduct(){
        mHomeInteractor.getAllProduct();
    }
    @Override
    public void getAllProductSuccess(AllProductResponse response) {
        if(mHomeView != null){
            mHomeView.getAllProductSuccess(response);
        }
    }

    @Override
    public void getAllProductError(String error) {
        if(mHomeView != null){
            mHomeView.getAllProductError(error);
        }
    }

    public void searchProduct(String query) {
        mHomeInteractor.searchProduct(query);
    }
    @Override
    public void searchSuccess(AllProductResponse response) {
        if(mHomeView != null){
            mHomeView.searchSuccess(response);
        }
    }

    @Override
    public void searchError(String error) {
        if(mHomeView != null){
            mHomeView.searchError(error);
        }
    }
}
