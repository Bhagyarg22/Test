package com.test.test.Services;


import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    public static ServiceApi serviceApi;

    public static Retrofit getApiClient(String baseUrl) {
        Log.e("RetrofitClient", baseUrl);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        try {
            httpClient.addInterceptor(new Interceptor() {

                @Override
                public Response intercept(Chain chain) throws IOException {


                    Request request = chain.request().newBuilder().build();

                    // return chain.proceed(request);
                    Response response = chain.proceed(request);
                    Log.e("MyApp", "Code : " + response.code());
                    String callingAPI = request.url().toString();



                    return response;
                }
            });
        } catch (RuntimeException e) {
            Log.e("exception", e.getMessage());
        }

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .baseUrl(baseUrl)
                .client(httpClient.build()).build();
        return retrofit;
    }

    public static ServiceApi getServiceApi() {
        serviceApi = RetrofitClient.getApiClient(Constant.BASE_URL).create(ServiceApi.class);
        return serviceApi;
    }
}



