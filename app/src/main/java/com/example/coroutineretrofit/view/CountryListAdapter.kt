package com.example.coroutineretrofit.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutineretrofit.R
import com.example.coroutineretrofit.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(private var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bindView(country)
    }

    override fun getItemCount(): Int = countries.size

    fun updateCountries(newCountries: ArrayList<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(country: Country) {
            itemView.name.text = country.countryName
            itemView.capital.text = country.capital
            itemView.imageView.loadImage(country.flag)
        }
    }
}