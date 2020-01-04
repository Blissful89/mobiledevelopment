package com.example.lendahand.model

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName="task_table")
data class Task(
    @SerializedName("title")
    var title: String,

    @SerializedName("date")
    var date: String,

    @SerializedName("descriptions")
    var descriptions: List<String>,

    @SerializedName("comments")
    var comments: List<String>,

    @SerializedName("image")
    var image: String,

    @SerializedName("completed")
    var completed: Boolean,

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
) : Parcelable {
    fun getTaskImage() = "https://picsum.photos$image"
}