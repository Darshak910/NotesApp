package com.example.roomdemo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val note: String
) : Parcelable
