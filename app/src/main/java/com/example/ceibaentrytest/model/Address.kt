package com.example.ceibaentrytest.model

import com.google.gson.annotations.SerializedName

data class Address(
    @field:SerializedName("city")
    val city: String? = null,
    @field:SerializedName("geo")
    val geo: Geo? = null,
    @field:SerializedName("street")
    val street: String? = null,
    @field:SerializedName("suite")
    val suite: String? = null,
    @field:SerializedName("zipcode")
    val zipcode: String? = null
)