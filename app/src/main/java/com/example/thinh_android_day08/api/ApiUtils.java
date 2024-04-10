package com.example.thinh_android_day08.api;

import com.example.thinh_android_day08.api.services.IDummyServices;

public class ApiUtils {
    public static IDummyServices getDummyServices(){
        return RetrofitClient.getInstance().create(IDummyServices.class);
    }
}
