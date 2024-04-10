package com.example.thinh_android_day08.api.services;

import com.example.thinh_android_day08.objects.AllProductResponse;
import com.example.thinh_android_day08.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IDummyServices {
    @GET(Constant.GET_ALL_PRODUCT)
    Call<AllProductResponse> getAllProduct();
    @GET(Constant.GET_PRODUCT_BY_SEARCH)
    Call<AllProductResponse> searchProduct(@Query("q") String query);
}
