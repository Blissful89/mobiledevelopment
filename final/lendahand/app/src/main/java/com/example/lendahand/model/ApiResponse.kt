package com.example.lendahand.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(@SerializedName("results") var tasks: List<Task>)