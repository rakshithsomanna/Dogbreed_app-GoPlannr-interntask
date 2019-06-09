
package com.example.dogbreeds

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.example.dogbreeds.ViewModel.DogBreedViewModel

class MainActivity : AppCompatActivity() {
    val urls = mutableListOf<String>()
    lateinit var ad1:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel: DogBreedViewModel = ViewModelProviders.of(this).get(DogBreedViewModel::class.java)
        observeViewModel(viewModel)

    }

    private fun observeViewModel(viewModel: DogBreedViewModel) {
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
                    searchview1.setOnQueryTextListener(object : SearchView.OnQueryTextListener
                    {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            ad1.filter.filter(newText)
                            return false
                        }
                    })

                }
            }
        })
        viewModel.getDogImageObservable().observe(this, object: Observer<String?> {
            override fun onChanged(imageurl: String?) {
                if (imageurl != null) {
                    urls.add(imageurl)

                    Log.d("",urls.toString())

                    if (urls.size == 3) {
                        val adapter = ViewPagerAdapter2(applicationContext, arrayOf(urls[0], urls[1], urls[2]))
                        view_pager.adapter = adapter
                    }

                }
            }
        })


    }

}
