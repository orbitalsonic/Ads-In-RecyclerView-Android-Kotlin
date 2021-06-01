package com.orbital.sonic.adsinrecyclerviewkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import java.util.*

class CountryAdapter(countryList: ArrayList<CountryItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mCountryList: ArrayList<CountryItem> = countryList
    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            1 -> {
                val v: View = inflater.inflate(R.layout.country_item, parent, false)
                viewHolder = CountryViewHolder(v, mListener)
            }
            2 -> {
                val v: View = inflater.inflate(R.layout.admob_ads, parent, false)
                viewHolder = ViewHolderAdMob(v)
            }
        }
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return mCountryList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mCountryList[position].typeView
    }

    fun removeCountryItem(mPosition: Int){
        mCountryList.removeAt(mPosition)
        notifyItemRemoved(mPosition)
    }

    fun getCountryItem(mPosition: Int): CountryItem {
       return mCountryList[mPosition]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem: CountryItem = mCountryList[position]

        when (holder.itemViewType) {
            1 -> {
                val viewHolder = holder as CountryViewHolder
                viewHolder.mTextView.text = currentItem.countryName
            }
            2 -> {
            }
        }
    }

    class CountryViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView = itemView.findViewById(R.id.textView)

        init {

            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }

        }

    }

    class ViewHolderAdMob(view: View) : RecyclerView.ViewHolder(view) {

        init {
            val adViewB: AdView = view.findViewById<View>(R.id.adViewB) as AdView
            val adRequest =
                AdRequest.Builder().build()
            adViewB.loadAd(adRequest)
        }
    }
}

