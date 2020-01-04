package com.example.studentportal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal(
    var name: String,
    var url: String
): Parcelable {
    companion object {
        val defaultPortals = arrayListOf<Portal?>(
            Portal("DLO", "https://dlo.mijnhva.nl/d2l/home")
        )
    }
}