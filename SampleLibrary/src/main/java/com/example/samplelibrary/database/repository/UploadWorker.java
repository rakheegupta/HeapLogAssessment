package com.example.samplelibrary.database.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadWorker extends Worker {
    public UploadWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
        setupRetrofit();
    }

    private List<Event> events;
    Retrofit retrofit;
    EventService eventService;

    public void setEvents(List<Event> _events){
        events = _events;
    }

    @Override
    public Result doWork() {

        // Do the work here--in this case, upload the images.
        //uploadEvents();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //RequestBody requestBody = RequestBody.create();
                Call<Event> call = eventService.uploadEvents(events);

                call.enqueue(new retrofit2.Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, retrofit2.Response<Event> response) {

                    }

                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {

                    }
                });
            }
        }).start();


        System.out.println("events uploaded");
        // Indicate whether the work finished successfully with the Result
        return Result.success();
    }

    void setupRetrofit(){
        Interceptor requestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                HttpUrl url = chain.request().url()
                        .newBuilder()
                        .addQueryParameter("api_key","69694a19e98df7f5c79b13285d536102")
                        .build();

                Request newRequest = chain.request().newBuilder().url(url).build();
                return chain.proceed(newRequest);
            }
        };
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(requestInterceptor).build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        eventService= retrofit.create(EventService.class);
    }

}


