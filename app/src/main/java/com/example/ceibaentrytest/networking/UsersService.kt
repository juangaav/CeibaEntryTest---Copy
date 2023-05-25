package com.example.ceibaentrytest.networking

import com.example.ceibaentrytest.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UsersService {
    @GET("users")
    fun getUsers(): Call<ArrayList<User>>

    companion object {
        var retrofitService: UsersService? = null

        //Create the Retrofit service instance using the retrofit.
        fun getInstance(): UsersService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(UsersService::class.java)
            }
            return retrofitService!!
        }
    }
}