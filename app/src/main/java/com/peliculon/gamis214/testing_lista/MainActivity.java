package com.peliculon.gamis214.testing_lista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.peliculon.gamis214.testing_lista.Service.ApiClient;
import com.peliculon.gamis214.testing_lista.Service.ApiInterface;
import com.peliculon.gamis214.testing_lista.model.FeedResponse;
import com.peliculon.gamis214.testing_lista.model.itemList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list_items);

        getService();

    }

    private void getService() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<FeedResponse> call = apiService.getListNames(2);
        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                Log.i(TAG,response.message());
                if(response.body() != null && response.isSuccessful()){
                    setList(response.body());
                }else{
                    Log.e(TAG,response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error to get the service",Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.toString());
            }
        });

    }

    private void setList(FeedResponse body) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter adapter = new customAdapter(this,body.getItems(),this);
        recyclerView.swapAdapter(adapter,true);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.container:
                itemList itemList = (itemList) view.getTag();
                Toast.makeText(this,"click:" + itemList.getName(),Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
