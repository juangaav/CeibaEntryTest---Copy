package com.example.ceibaentrytest

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ceibaentrytest.databinding.MainActivityBinding
import com.example.ceibaentrytest.networking.UsersRepository
import com.example.ceibaentrytest.networking.UsersService
import com.example.ceibaentrytest.viewmodel.HomeViewModel
import com.example.ceibaentrytest.viewmodel.HomeAdapter
import com.example.ceibaentrytest.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: MainActivityBinding

    lateinit var viewModel: HomeViewModel
    private val usersService = UsersService.getInstance()
    val adapter = HomeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)

        supportActionBar?.title = "Prueba de Ingreso"
        val colorValue = ContextCompat.getColor(this, R.color.green)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(colorValue))
        setContentView(binding.root)
        viewModel =
            ViewModelProvider(this, ViewModelFactory(UsersRepository(usersService))).get(
                HomeViewModel::class.java
            )

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        //set recyclerview adapter
        binding.recyclerview.adapter = adapter

        viewModel.userList.observe(this, Observer {
            adapter.setUserList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.d(TAG, "errorMessage: $it")
        })

        viewModel.getUsers()
    }
}

