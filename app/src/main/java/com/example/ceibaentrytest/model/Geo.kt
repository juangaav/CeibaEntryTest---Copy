package com.example.ceibaentrytest.model

import com.google.gson.annotations.SerializedName

data class Geo(
    @field:SerializedName("lat")
    val lat: String? = null,
    @field:SerializedName("lng")
    val lng: String? = null
)