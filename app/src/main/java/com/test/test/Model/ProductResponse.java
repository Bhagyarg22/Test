package com.test.test.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductResponse{

	@SerializedName("ProductResponse")
	private List<ProductResponseItem> productResponse;

	public List<ProductResponseItem> getProductResponse(){
		return productResponse;
	}
}