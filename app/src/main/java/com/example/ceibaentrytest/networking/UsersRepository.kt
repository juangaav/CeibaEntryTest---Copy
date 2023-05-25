package com.example.ceibaentrytest.networking

class UsersRepository constructor(private val usersService: UsersService) {
    fun getUsers() = usersService.getUsers()
}
