package com.example.data.remote

import com.example.data.response.EventResponse
import com.example.data.response.Responses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/events")
    fun getListEvents(@Query("active") page: String): Call<EventResponse>

    @GET("/events/{id}")
    fun getEventDetail(@Path("id") id: Int): Call<Responses>
}