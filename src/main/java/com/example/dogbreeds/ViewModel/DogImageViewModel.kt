package com.example.dogbreeds.ViewModel

import android.arch.lifecycle.LiveData
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Transformations
import com.example.dogbreeds.Model.data.BreedList
import com.example.dogbreeds.Model.data.DogImage
import com.example.dogbreeds.Repository.BreedRepository


class DogImageViewModel(application: Application) : AndroidViewModel(application) {

    private val dogImageObservable1: LiveData<DogImage>

    init {

        val rep = BreedRepository()
        dogImageObservable1 = rep.getImageUrls()
    }

    fun getDogImageObservable(): LiveData<String> {
        val imagelist:LiveData<String> = Transformations.map(dogImageObservable1){
            DogImage->DogImage.message.toString()
        }
        return imagelist
    }
}