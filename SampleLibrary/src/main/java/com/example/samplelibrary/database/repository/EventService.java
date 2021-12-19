package com.example.samplelibrary.database.repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EventService {

    @POST("app/events")
    Call<Event> uploadEvents(@Body List<Event> events);

}
