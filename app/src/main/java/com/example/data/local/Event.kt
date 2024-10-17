package com.example.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "events")
data class Event (
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey(autoGenerate = false)
    var id: String = "",

    @field:ColumnInfo("name")
    var name: String = "",

    @field:ColumnInfo("description")
    var description: String = "",

    @field:ColumnInfo("mediaCover")
    var mediaCover: String? = null,
): Parcelable