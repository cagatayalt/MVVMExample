package com.cagatayalt.github.countriesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cagatayalt.github.countriesapp.R
import com.cagatayalt.github.countriesapp.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countryList : ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.countryRowNameTV.text = countryList[position].countryName
        holder.view.countryRowRegionTV.text = countryList[position].countryRegion
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    // If the page is swiped down, we want to update the recycler vie w
    fun updateCountryList(newCountryList : List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}



