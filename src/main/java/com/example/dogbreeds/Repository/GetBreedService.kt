package com.example.dogbreeds.Repository

import com.example.dogbreeds.Model.data.BreedList
import com.example.dogbreeds.Model.data.DogImage
import retrofit2.Call
import retrofit2.http.GET

interface GetBreedService {


    @GET("/api/breeds/list/all")
    fun getAllBreeds() : Call<BreedList>

    @GET("/api/breeds/image/random")
    fun getRandomImage() : Call<DogImage>
}