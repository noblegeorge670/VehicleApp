package com.example.noblegeorge.vehicleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Images> imagesList;
    private RecyclerView recyclerView;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imagesList = new ArrayList<>();

        ApiInterface service = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<String> call = service.getData();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                getPayLoad(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void getPayLoad(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject subObj = jsonObject.getJSONObject("payload");
            JSONArray jArr = subObj.getJSONArray("body");
            for (int i=0; i < jArr.length(); i++) {
                JSONObject obj = jArr.getJSONObject(i);
                if(obj.getString("ctype").equalsIgnoreCase("oembed")){
                    JSONObject vehicleData = obj.getJSONObject("data");
                    Images image = new Images();
                    image.setImageUrl(vehicleData.getString("list_url"));
                    imagesList.add(image);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        adapter = new ImageAdapter(MainActivity.this,imagesList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }



}
