package com.example.data.local

import androidx.lifecycle.LiveData
import com.example.data.remote.ApiService

class DetailRepository private constructor(
    private val apiService: ApiService,
    private val eventDao: EventDao){


    fun getFavoriteEventById(id: String): LiveData<Event> {
        return eventDao.getFavEventById(id)
    }

     fun insertEvent(event: Event) {
        eventDao.insert(event)
    }

     fun  deleteEvent(event: Event) {
        eventDao.delete(event)

    }

    companion object {
        @Volatile
        private var instance: DetailRepository? = null
        fun getInstance(
            apiService: ApiService,
            eventDao: EventDao,
        ): DetailRepository =
            instance ?: synchronized(this) {
                instance ?: DetailRepository(apiService, eventDao)
            }.also { instance = it }
    }
}