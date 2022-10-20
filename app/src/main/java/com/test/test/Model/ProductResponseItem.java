package com.test.test.Model;

import com.google.gson.annotations.SerializedName;

public class ProductResponseItem{

	@SerializedName("author")
	private String author;

	@SerializedName("width")
	private int width;

	@SerializedName("download_url")
	private String downloadUrl;

	@SerializedName("id")
	private String id;

	@SerializedName("url")
	private String url;

	@SerializedName("height")
	private int height;

	public String getAuthor(){
		return author;
	}

	public int getWidth(){
		return width;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getDownloadUrl(){
		return downloadUrl;
	}

	public String getId(){
		return id;
	}

	public String getUrl(){
		return url;
	}

	public int getHeight(){
		return height;
	}
}