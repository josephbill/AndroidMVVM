package com.example.hrapp

import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
//     HTTP Requests (GET. POST. UPDATE. DELETE)
    @GET("/users")
    suspend fun fetchUsers() : List<User>

}