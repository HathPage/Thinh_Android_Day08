package com.example.thinh_android_day08.interactors;

import com.example.thinh_android_day08.api.ApiUtils;
import com.example.thinh_android_day08.api.services.IDummyServices;
import com.example.thinh_android_day08.interfaces.IHomePresenter;
import com.example.thinh_android_day08.objects.AllProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeInteractor {
    private IHomePresenter mHomePresenter;
    private IDummyServices mDummyServices;
    public HomeInteractor(IHomePresenter HomePresenter) {
        this.mHomePresenter = HomePresenter;
        mDummyServices = ApiUtils.getDummyServices();
    }
    public void getAllProduct(){
        mDummyServices.getAllProduct().enqueue(new Callback<AllProductResponse>() {
            @Override
            public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
                if(response.isSuccessful() && response.code() == 200){
                    if(mHomePresenter!=null) {
                        mHomePresenter.getAllProductSuccess(response.body());
                    }
                }else {
                    if(mHomePresenter!=null) {
                        mHomePresenter.getAllProductError(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<AllProductResponse> call, Throwable throwable) {
                mHomePresenter.getAllProductError(throwable.getMessage());
            }
        });
    }
    public void searchProduct(String query){
        mDummyServices.searchProduct(query).enqueue(new Callback<AllProductResponse>() {
            @Override
            public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
                if(response.isSuccessful() && response.code() == 200){
                    if(mHomePresenter!=null) {
                        mHomePresenter.searchSuccess(response.body());
                    }
                }else {
                    if(mHomePresenter!=null) {
                        mHomePresenter.searchError(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<AllProductResponse> call, Throwable throwable) {
                mHomePresenter.getAllProductError(throwable.getMessage());
            }
        });
    }
}
