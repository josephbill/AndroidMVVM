package com.example.hrapp

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// we use this module/class to provide dependencies to other parts/files of the android app using the HILT D.I framework
// installin : specifies our class should be installed in the SingletonComponent of the HILT framework

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    // method provides an instance of the retrofit class
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //method to provide the instance of the Interface class that defines the HTTP methods (GET. POST. UPDATE. DELETE) that the app uses to
    // make the requests.

    @Singleton
    @Provides
    fun provideSerService(retrofit: Retrofit) : UserService {
        return retrofit.create(UserService::class.java)
    }
}












