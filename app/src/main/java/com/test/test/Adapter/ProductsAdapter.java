package com.test.test.Adapter;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.test.MainActivity;
import com.test.test.Model.ProductResponseItem;
import com.test.test.R;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ProductResponseItem> productList;
    Context context;
    DownloadManager manager;

    public ProductsAdapter(List<ProductResponseItem>productList, Context context) {
        this.productList = productList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_layout,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;



        viewHolder.tv_title.setText(productList.get(position).getAuthor());
        Log.e("urlll",""+productList.get(position).getDownloadUrl());


        Glide.with(context).load(productList.get(position).getDownloadUrl()).into(viewHolder.iv_prdct);

       viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ((MainActivity)context).showItem(productList,position);
           }
       });



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }




    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_prdct;
        TextView tv_title;


        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_prdct = itemView.findViewById(R.id.iv_prdct);
            tv_title = itemView.findViewById(R.id.tv_title);



        }
    }
}
