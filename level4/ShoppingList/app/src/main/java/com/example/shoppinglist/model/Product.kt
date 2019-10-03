package com.example.shoppinglist.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "productTable")
data class Product(

    @ColumnInfo(name = "product")
    var name: String,

    @ColumnInfo(name = "quantity")
    var quantity: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

) : Parcelable
