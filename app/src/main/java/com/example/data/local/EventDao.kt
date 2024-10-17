package com.example.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(event: Event)

    @Delete
     fun delete(event: Event)

    @Query("SELECT * from events ORDER BY id ASC")
    fun getAllFavEvent(): LiveData<List<Event>>

    @Query("SELECT * FROM events WHERE id = :id")
    fun getFavEventById(id: String): LiveData<Event>

}