package com.test.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

import com.test.test.Adapter.ProductsAdapter;
import com.test.test.Model.ProductResponseItem;
import com.test.test.viewModels.ProductVModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_products;
    ProductVModel productVModel;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    ProgressDialog   progress;
    ProductsAdapter productsAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();
    }

    private void initialise() {

        rv_products = findViewById(R.id.rv_products);
        swipeRefreshLayout = findViewById(R.id.swipe);

        swipeRefreshLayout.setRefreshing(true);

        productVModel = ViewModelProviders.of(this).get(ProductVModel.class);

        productVModel.init();
        recyclerViewLayoutManager = new LinearLayoutManager(this) ;

        // Set LayoutManager on Recycler View
        rv_products.setLayoutManager(recyclerViewLayoutManager);

        getProductList();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProductList();
            }
        });

    }



    private void getProductList() {

        progress = new ProgressDialog(MainActivity.this);
        productVModel.productMutableLiveData().observe(this, new Observer<List<ProductResponseItem>>() {
            @Override
            public void onChanged(List<ProductResponseItem> productResponses) {

                progress.setMessage("loading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(true);
                progress.setCanceledOnTouchOutside(false);
                progress.setProgress(0);
                progress.show();
                if (swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);
                if (productResponses.size()!=0 && productResponses!=null){
                    progress.dismiss();
                    productsAdapter = new ProductsAdapter(productResponses,MainActivity.this);
                    rv_products.setAdapter(productsAdapter);
                    productsAdapter.notifyDataSetChanged();

                }

            }
        });
    }

    public void showItem(List<ProductResponseItem> productList, int position) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_layout);

        WebView webView = dialog.findViewById(R.id.webviw);

        webView.loadUrl(productList.get(position).getUrl());


        dialog.show();
    }
}