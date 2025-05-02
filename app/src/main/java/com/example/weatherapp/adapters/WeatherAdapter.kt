package com.example.weatherapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class WeatherAdapter(val listener: Listener?): ListAdapter<WeatherModel, WeatherAdapter.Holder>(Comparator()) {
    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int,
    ): Holder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_item, p0, false)
        return Holder(view, listener)
    }

    override fun onBindViewHolder(
        p0: Holder,
        p1: Int,
    ) {
        p0.bind(getItem(p1))
    }

    class Holder(view: View, val listener: Listener?): RecyclerView.ViewHolder(view){
        val binding = ListItemBinding.bind(view)
        var itemTemp: WeatherModel? = null
        init{
            itemView.setOnClickListener {
                itemTemp?.let { item -> listener?.onClick(item) }
            }
        }
        fun bind(item: WeatherModel) = with(binding){
            Log.d("WeatherAdapter", "City: ${item.city}")
            itemTemp= item
            tvDate.text = item.time
            tvCondition.text = item.condition
            tvTemp.text = item.currentTemp.ifEmpty { "${item.maxTemp}℃ / ${item.minTemap}℃" }
            Picasso.get().load("https:"+item.imageUrl).into(im)
        }
    }
    class Comparator: DiffUtil.ItemCallback<WeatherModel>(){
        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean{
            return newItem==oldItem
        }

        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean{
            return newItem==oldItem
        }

    }

    interface Listener{
        fun onClick(item: WeatherModel)
    }
}