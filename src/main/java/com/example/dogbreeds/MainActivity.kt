
package com.example.dogbreeds

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.dogbreeds.Model.data.BreedList
import kotlinx.android.synthetic.main.activity_main.*
import com.example.dogbreeds.ViewModel.BreedListViewModel

import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import android.widget.ArrayAdapter
import com.example.dogbreeds.Model.data.DogImage
import com.example.dogbreeds.ViewModel.DogImageViewModel

class MainActivity : AppCompatActivity() {
    val urls = mutableListOf<String>()
    //var x:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel: BreedListViewModel = ViewModelProviders.of(this).get(BreedListViewModel::class.java)
        observeViewModel(viewModel)
        val viewModel2: DogImageViewModel = ViewModelProviders.of(this).get(DogImageViewModel::class.java)
        observeViewModel2(viewModel2)
    }

    private fun observeViewModel(viewModel: BreedListViewModel) {
        viewModel.getBreedListObservable().observe(this, object: Observer<ArrayList<String>?> {
            override fun onChanged(breeds: ArrayList<String>?) {
                if (breeds != null) {

                    for (i in 0 until breeds.size) {
                        if (breeds[i].last() == '[') {
                            breeds[i] = " "+breeds[i].substringBefore("=")
                            breeds[i].trimStart()
                        } else {
                            breeds[i] = " "+breeds[i] + "]"
                            breeds[i].trimStart()

                        }
                    }


                    val ad1 = ArrayAdapter(applicationContext,
                    R.layout.list_item, breeds)
                    listview1.adapter = ad1

                }
            }
        })
    }
    private fun observeViewModel2(viewModel: DogImageViewModel) {
        viewModel.getDogImageObservable().observe(this, object: Observer<String?> {
            override fun onChanged(imageurl: String?) {
                if (imageurl != null) {
                    urls.add(imageurl)

                    Log.d("",urls.toString())
                    try {
                        val adapter = ViewPagerAdapter2(applicationContext, arrayOf(urls[0], urls[1], urls[2]))
                        view_pager.adapter = adapter
                    }
                    catch (e: IllegalStateException)
                    {
                        Log.d("","Image accessed before it is loaded")
                    }
                    catch (e:IndexOutOfBoundsException)
                    {
                        Log.d("","Index out of bounds. Image accessed before it is loaded")
                    }
                }
            }
        })


    }

}