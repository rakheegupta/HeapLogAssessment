package com.example.heaploglibrary.database.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;

public class UploadWorker extends Worker {

    public UploadWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    EventService eventService = EventService.initEventService();
    EventRepository eventRepository = EventRepository.getInstance();
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public Result doWork() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Event> events = eventRepository.getEventsToUpload();
                if (events != null && events.size() > 0) {
                    long[] ids = getAllIds(events);

                    Data data = setupGsonData(events);

                    Log.d("LogLib","upload started for "+ Arrays.toString(ids));
                    eventService.uploadEvents(data.getString("event"))
                    .enqueue(new Callback<Event>() {
                        @Override
                        public void onResponse(Call<Event> call, retrofit2.Response<Event> response) {
                            Log.d("LogLib","Reached on success");

                            executorService.execute(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("LogLib","upload successful for "+Arrays.toString(ids));
                                    int result = eventRepository.deleteEvents(ids);
                                    Log.d("LogLib","#deleted- "+result);
                                    // if there is more data in the local cache then upload all of that
                                    doWork();
                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<Event> call, Throwable t) {
                            Log.d("LogLib","Upload Failed- "+t.getLocalizedMessage());
                        }
                    });
                }
            }
        });
        return Result.success();
    }

    long[] getAllIds(List<Event> events) {
        long[] ids = new long[events.size()];
        for(int i=0;i<events.size();i++){
            ids[i]=events.get(i).getId();
        }
        return ids;
    }

    Data setupGsonData(List<Event> events) {
        // Define the input
        Type listOfEventObject = new TypeToken<List<Event>>(){}.getType();
        Gson gson = new Gson();
        String events_string = gson.toJson(events, listOfEventObject);

        Data eventData = new Data.Builder()
                .putString("event",events_string)
                .build();

        return eventData;
    }
}


