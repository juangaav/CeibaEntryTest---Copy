package com.example.ceibaentrytest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ceibaentrytest.model.User
import com.example.ceibaentrytest.networking.UsersRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel (private val repository: UsersRepository) : ViewModel() {

    val userList = MutableLiveData<List<User>>()
    val errorMessage = MutableLiveData<String>()

    fun getUsers() {
        val response = repository.getUsers()
        response.enqueue(object : Callback<ArrayList<User>> {

            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                userList.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}