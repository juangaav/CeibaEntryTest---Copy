package com.example.ceibaentrytest.model

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("address")
    val address: Address? = null,
    @field:SerializedName("company")
    val company: Company? = null,
    @field:SerializedName("email")
    val email: String? = null,
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("phone")
    val phone: String? = null,
    @field:SerializedName("username")
    val username: String? = null,
    @field:SerializedName("website")
    val website: String? = null
)