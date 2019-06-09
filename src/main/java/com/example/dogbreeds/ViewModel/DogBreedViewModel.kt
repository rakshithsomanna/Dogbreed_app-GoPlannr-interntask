package com.example.dogbreeds.ViewModel

import android.arch.lifecycle.LiveData
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Transformations
import com.example.dogbreeds.Model.data.BreedList
import com.example.dogbreeds.Model.data.DogImage
import com.example.dogbreeds.Repository.BreedRepository


class DogBreedViewModel(application: Application) : AndroidViewModel(application) {

    private val breedListObservable1: LiveData<BreedList>
    private val dogImageObservable1: LiveData<DogImage>

    init {

        val rep = BreedRepository()
        breedListObservable1 = rep.getBreedList()
        dogImageObservable1 = rep.getImageUrls()
    }
    fun getBreedListObservable(): LiveData<ArrayList<String>> {
        val breedlist: LiveData<ArrayList<String>> = Transformations.map(breedListObservable1) { BreedList ->
            BreedList.message.toString().substring(8).split("],") as ArrayList<String>
        }
        return breedlist
    }
    fun getDogImageObservable(): LiveData<String> {
        val imagelist:LiveData<String> = Transformations.map(dogImageObservable1){
                DogImage->DogImage.message.toString()
        }
        return imagelist
    }

}