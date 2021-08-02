package com.example.foodsapi;

import com.example.foodsapi.response.ExampleResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/api/json/v1/1/categories.php")
    Call<ExampleResponse> getAllInfo();
}


