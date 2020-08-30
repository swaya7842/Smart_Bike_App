package com.example.lironautomotivepvtltd.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    public static final String BASE_URL = "http://192.168.43.58/nironautomotive/";
    public static Retrofit retrofit = null;

//    static final OkHttpClient getRequestHeader() {
//
//        OkHttpClient  okHttp = new OkHttpClient.Builder()
//                .connectTimeout(20, TimeUnit.SECONDS)
//                .writeTimeout(20, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .build();
//        return okHttp;
//    }
    public static Retrofit getApiClient(){

        if (retrofit == null){

            retrofit =new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;

    }
}
