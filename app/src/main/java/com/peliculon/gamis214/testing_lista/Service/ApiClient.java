package com.peliculon.gamis214.testing_lista.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sony on 31/10/2016.
 */

public class ApiClient {

    public static final String BASE_URL = "http://www.json-generator.com//";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getFormatGson()))
                    .client(setInterceptorHttp().build())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient.Builder setInterceptorHttp(){
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //-->LOG LEVE
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(logging);

        return okHttpClient;
    }

    private static Gson getFormatGson(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create();
        return gson;
    }

}
