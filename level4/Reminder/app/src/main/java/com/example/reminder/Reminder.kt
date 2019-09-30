package com.example.reminder

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "reminderTable")
data class Reminder(

    @ColumnInfo(name = "reminder")
    var reminder: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

) : Parcelable
