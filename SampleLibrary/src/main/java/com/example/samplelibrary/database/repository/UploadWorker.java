package com.example.samplelibrary.database.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadWorker extends Worker {
    public UploadWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    private List<Event> events;
    long firstEvent,lastEvent;
    Retrofit retrofit;
    EventService eventService;


    public void setEvents(List<Event> _events){
        events = _events;
    }

    @Override
    public Result doWork() {
        // Do the work here--
        //get events from database

            new Thread(new Runnable() {
                @Override
                public void run() {
                    events = EventRepository.getInstance().getEventDao().getEventsToUpload();
                    if (events != null && events.size() > 0) {
                        long[] ids = getAllIds(events);

                        Data data = setupGsonData();

                        //post it to the network
                        setupRetrofit();
                        System.out.println("upload started for "+ Arrays.toString(ids));
                        eventService.uploadEvents(data.getString("event"))
                        .enqueue(new Callback<Event>() {
                            @Override
                            public void onResponse(Call<Event> call, retrofit2.Response<Event> response) {
                                System.out.println("Reached on success");

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("upload successful for "+Arrays.toString(ids));
                                        int result = EventRepository.getInstance().getEventDao().deleteEvents(ids);
                                        System.out.println("deleted"+result);
                                    }
                                }).start();

                            }

                            @Override
                            public void onFailure(Call<Event> call, Throwable t) {
                                System.out.println("Reached on faliure");

                            }
                        });
                    }
                }
            }).start();


        //System.out.println("events uploaded");
        // Indicate whether the work finished successfully with the Result
        return Result.success();
    }

    private long[] getAllIds(List<Event> events) {
        long[] ids = new long[events.size()];
        for(int i=0;i<events.size();i++){
            ids[i]=events.get(i).getId();
        }
        return ids;
    }


    void setupRetrofit(){
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
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://httpbin.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        eventService= retrofit.create(EventService.class);
    }


    Data setupGsonData() {
        // Define the input

        Type listOfEventObject = new TypeToken<List<Event>>(){}.getType();
        Gson gson = new Gson();
        String events_string = gson.toJson(events, listOfEventObject);

        Data eventData = new Data.Builder()
                .putString("event",events_string)
                .build();

        return eventData;
    }

    private Callback<Event> eventUploadCallback = new Callback<Event>() {
        @Override
        public void onResponse(Call<Event> call, retrofit2.Response<Event> response) {
            System.out.println("Reached on success");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    //int result = EventRepository.getInstance().getEventDao().deleteEvents(ids);
                    System.out.println("deleted" );
                }
            }).start();

        }

        @Override
        public void onFailure(Call<Event> call, Throwable t) {
            System.out.println("Reached on faliure");
        }
    };
}


