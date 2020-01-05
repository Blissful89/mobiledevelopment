package com.example.lendahand.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(
    @SerializedName("name")
    var name: String,

    @SerializedName("password")
    var password: String
) : Parcelable