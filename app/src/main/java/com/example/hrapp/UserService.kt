package com.example.hrapp

import com.example.hrapp.postupdate.model.DataModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {
//     HTTP Requests (GET. POST. UPDATE. DELETE)
    @GET("/users")
    suspend fun fetchUsers() : List<User>

    //
    @POST("users")
    fun postData(@Body dataModel: DataModel?) : Call<DataModel?>?
// the update process is similar to the post only diff. is the endpoint for update needs to reference the unique record to be updated.
    // PUT / http verb for update.
    @PUT("users/2")
    fun updateData(@Body dataModel: DataModel?) : Call<DataModel?>?

}