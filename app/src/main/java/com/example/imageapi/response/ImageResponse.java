package com.example.imageapi.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ImageResponse{

	@SerializedName("hits")
	private List<HitsItem> hits;

	@SerializedName("total")
	private int total;

	@SerializedName("totalHits")
	private int totalHits;

	public List<HitsItem> getHits(){
		return hits;
	}

	public int getTotal(){
		return total;
	}

	public int getTotalHits(){
		return totalHits;
	}
}