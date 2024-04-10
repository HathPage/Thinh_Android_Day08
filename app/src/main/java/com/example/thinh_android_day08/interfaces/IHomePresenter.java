package com.example.thinh_android_day08.interfaces;

import com.example.thinh_android_day08.objects.AllProductResponse;

public interface IHomePresenter {
    void getAllProductSuccess(AllProductResponse response);
    void getAllProductError(String error);
    void searchProduct(String query);

    void searchSuccess(AllProductResponse response);

    void searchError(String error);
}
