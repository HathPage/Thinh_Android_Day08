package com.example.thinh_android_day08.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://dummyjson.com/";
    public static Retrofit instances, searchInstances;
    public static Retrofit getInstance(){
        if(instances == null){
            instances = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instances;
    }
    public static Retrofit searchInstance(){
        if(searchInstances == null){
            searchInstances = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return searchInstances;
    }
}
