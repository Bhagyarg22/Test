package com.test.test.Repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.google.gson.Gson;
import com.test.test.Model.ProductResponseItem;
import com.test.test.Services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductRepo {
    private static ProductRepo instance;
    MutableLiveData<List<ProductResponseItem>> productMutableData;
    int page =2,limit = 20;

    private  List<ProductResponseItem> productList = new ArrayList<>();


    public static ProductRepo getInstance() {

        if (instance == null) {
            instance = new ProductRepo();
        }
        return instance;
    }

    public MutableLiveData<List<ProductResponseItem>> getProductsList() {
        productMutableData = new MutableLiveData<>();
        productMutableData.setValue(productList);

        getProducts();
        return productMutableData;
    }


    private void getProducts() {
        productList.clear();

        Call<List<ProductResponseItem>> call = RetrofitClient.getServiceApi().getProducts(page,limit);
        call.enqueue(new Callback<List<ProductResponseItem>>() {
            @Override
            public void onResponse(Call<List<ProductResponseItem>> call, Response<List<ProductResponseItem>> response) {

                Log.e("jsongson",""+ new Gson().toJson(response.body().get(0).getAuthor()));
                for (int i=0;i<response.body().size();i++){
                    productList.add(response.body().get(i));

                }
                productMutableData.setValue(productList);

            }

            @Override
            public void onFailure(Call<List<ProductResponseItem>> call, Throwable t) {

                Log.e("Code is getting faliure",t.toString());
            }
        });


        }




}

