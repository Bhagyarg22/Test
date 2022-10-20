package com.test.test.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.test.test.Model.ProductResponseItem;
import com.test.test.Repo.ProductRepo;

import java.util.List;

public class ProductVModel extends ViewModel {

    MutableLiveData<List<ProductResponseItem>> productLiveData;

    private ProductRepo mRepo;

    public void init(){
        mRepo = ProductRepo.getInstance();
    }

    public MutableLiveData<List<ProductResponseItem>> productMutableLiveData(){
        productLiveData = mRepo.getProductsList();
        return productLiveData;
    }




}
