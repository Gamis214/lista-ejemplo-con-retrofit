package com.peliculon.gamis214.testing_lista.Service;

import com.peliculon.gamis214.testing_lista.model.FeedResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sony on 31/10/2016.
 */

public interface ApiInterface {

    @GET("api/json/get/cnMbcFXGCq")
    Call<FeedResponse> getListNames(@Query("indent") int param);

}
