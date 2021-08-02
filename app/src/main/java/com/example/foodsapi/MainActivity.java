package com.example.foodsapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.foodsapi.adapter.ExampleAdapter;
import com.example.foodsapi.model.ExampleModel;
import com.example.foodsapi.response.ExampleResponse;
import com.example.foodsapi.retrofit.ApiRetrofit;

import java.util.EmptyStackException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<ExampleModel> dataList;
    RecyclerView recyclerView;
    ExampleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServerClient();
    }

    private void ServerClient() {



        ApiInterface apiInterface = ApiRetrofit.getRetrofit().create(ApiInterface.class);
        Call<ExampleResponse> call = apiInterface.getAllInfo();
        call.enqueue(new retrofit2.Callback<ExampleResponse>() {
            @Override
            public void onResponse(Call<ExampleResponse> call, Response<ExampleResponse> response) {
                generatorDataList(response.body().getCategories());

            }

            @Override
            public void onFailure(Call<ExampleResponse> call, Throwable t) {
                Log.d("error", t.getLocalizedMessage());

            }
        });


    }

    private void generatorDataList(List<ExampleModel> dataList1) {

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ExampleAdapter(dataList1,this);
        recyclerView.setAdapter(adapter);


    }

}