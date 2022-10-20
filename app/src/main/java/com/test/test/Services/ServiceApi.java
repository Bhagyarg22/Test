package com.test.test.Services;


import com.test.test.Model.ProductResponse;
import com.test.test.Model.ProductResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {

    //ClassList
    @GET("v2/list")
    Call<List<ProductResponseItem>> getProducts(@Query("page") int page,
                                            @Query("limit") int limit) ;




}
