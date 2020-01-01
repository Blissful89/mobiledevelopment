package com.example.lendahand.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task(
    @SerializedName("title") var title: String,
    @SerializedName("date") var date: String,
    @SerializedName("descriptions") var descriptions: ArrayList<String>,
    @SerializedName("comments") var comments: ArrayList<String>,
    @SerializedName("image") var image: String,
    @SerializedName("completed") var completed: Boolean
) : Parcelable {
    fun getTaskImage() = "https://image.tmdb.org/t/p/original$image"
}