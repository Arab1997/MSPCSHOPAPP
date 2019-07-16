package com.ukuya.mspc.api;

import com.ukuya.mspc.api.model.EventResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("events")
    Observable<EventResponse> getEventList(@Query("page") Integer page, @Query("expand") String expand);
}
