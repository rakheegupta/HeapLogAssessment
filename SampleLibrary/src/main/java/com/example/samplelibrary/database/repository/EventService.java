package com.example.samplelibrary.database.repository;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EventService {

    @POST("post")
    Call<Event> uploadEvents(@Body String events);

}
