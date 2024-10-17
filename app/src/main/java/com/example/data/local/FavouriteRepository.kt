package com.example.data.local

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavouriteRepository(application: Application) {
    private val mEventDao: EventDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = EventDatabase.getDatabase(application)
        mEventDao = db.eventDao()
    }

    fun insert(event: Event) {
        executorService.execute { mEventDao.insert(event) }
    }

    fun delete(event: Event) {
        executorService.execute { mEventDao.delete(event) }
    }

    fun getAllFavEvent(): LiveData<List<Event>> = mEventDao.getAllFavEvent()

    fun getEventById(id: String): LiveData<Event> = mEventDao.getFavEventById(id)

}