package com.example.dogbreeds.Repository

import android.widget.Toast
import com.example.dogbreeds.Model.data.BreedList
import com.example.dogbreeds.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData

import com.example.dogbreeds.MainActivity
import com.example.dogbreeds.Model.data.DogImage


class BreedRepository {
    fun getBreedList(): LiveData<BreedList> {
        val data = MutableLiveData<BreedList>()
        val service = RetrofitClientInstance.retrofitInstance?.create(GetBreedService::class.java)
        val call = service?.getAllBreeds()
        call?.enqueue(object : Callback<BreedList> {
            override fun onResponse(call: Call<BreedList>?, response: Response<BreedList>?) {
                data.value =response?.body();
            }

            override fun onFailure(call: Call<BreedList>?, t: Throwable?) {
                Toast.makeText(MainActivity(), "Error reading JSON", Toast.LENGTH_LONG).show()
            }

        })
        return data
    }


    fun getImageUrls(): LiveData<DogImage> {

        val data = MutableLiveData<DogImage>()
        val service2 = RetrofitClientInstance.retrofitInstance?.create(GetBreedService::class.java)
        for (i in 0..2) {
            val call2 = service2?.getRandomImage()
            call2?.enqueue(object : Callback<DogImage> {
                override fun onResponse(call: Call<DogImage>?, response: Response<DogImage>?) {
                    data.value =response?.body()
                }

                override fun onFailure(call: Call<DogImage>?, t: Throwable?) {
                    Toast.makeText(MainActivity(), "Error reading JSON", Toast.LENGTH_LONG).show()
                }

            })

        }

        return data

    }
}

