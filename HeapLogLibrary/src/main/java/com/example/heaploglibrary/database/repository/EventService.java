package com.example.heaploglibrary.database.repository;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EventService {

    @POST("post")
    Call<Event> uploadEvents(@Body String events);

    //setup retrofit to call remote server
    static EventService initEventService(){
        Interceptor requestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                HttpUrl url = chain.request().url()
                        .newBuilder()
                        .build();

                Request newRequest = chain.request().newBuilder().url(url).build();
                return chain.proceed(newRequest);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(requestInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EventService.class);
    }
}
