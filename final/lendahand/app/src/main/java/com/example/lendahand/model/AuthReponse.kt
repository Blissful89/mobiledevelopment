package com.example.lendahand.model

import com.google.gson.annotations.SerializedName

data class AuthResponse(@SerializedName("account") var account: Account)