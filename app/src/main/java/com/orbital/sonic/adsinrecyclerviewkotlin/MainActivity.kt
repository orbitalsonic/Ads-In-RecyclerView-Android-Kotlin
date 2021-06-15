package com.orbital.sonic.adsinrecyclerviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

   private lateinit var mRecyclerView: RecyclerView
   private lateinit var mCountryList: ArrayList<CountryItem>
   private lateinit var mLayoutManager: RecyclerView.LayoutManager
   private lateinit var mAdapter: CountryAdapter
   private lateinit var countryName: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createCountryList()
        buildRecyclerView()
    }

    private fun buildRecyclerView() {
        mRecyclerView= findViewById(R.id.recyclerView)
        mLayoutManager= LinearLayoutManager(this)
        mAdapter= CountryAdapter(mCountryList)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter


        mAdapter.setOnItemClickListener(object : CountryAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity,  mAdapter.getCountryItem(position).countryName, Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun createCountryList() {
        countryName = resources.getStringArray(R.array.country_name_list)
        mCountryList = ArrayList()

        for (index in countryName.indices){
            mCountryList.add(CountryItem( countryName[index],1))

            if (index % 3 == 0) {
                mCountryList.add(CountryItem( "Ads",2))
            }
        }

    }
}