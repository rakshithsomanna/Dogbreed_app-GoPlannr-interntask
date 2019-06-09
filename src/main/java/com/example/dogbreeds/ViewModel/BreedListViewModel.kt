package com.example.dogbreeds.ViewModel

import android.arch.lifecycle.LiveData
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Transformations
import com.example.dogbreeds.Model.data.BreedList
import com.example.dogbreeds.Repository.BreedRepository


class BreedListViewModel(application: Application) : AndroidViewModel(application) {

    private val breedListObservable1: LiveData<BreedList>

    init {

        val rep = BreedRepository()
        breedListObservable1 = rep.getBreedList()

    }

    fun getBreedListObservable(): LiveData<ArrayList<String>> {
        val breedlist: LiveData<ArrayList<String>> = Transformations.map(breedListObservable1) { BreedList ->
            BreedList.message.toString().substring(8).split("],") as ArrayList<String>
        }
        return breedlist
    }
}