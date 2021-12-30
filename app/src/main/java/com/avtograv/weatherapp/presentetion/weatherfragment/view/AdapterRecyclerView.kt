package com.avtograv.weatherapp.presentetion.weatherfragment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avtograv.weatherapp.databinding.ItemCurrentWeatherBinding
import com.avtograv.weatherapp.databinding.ItemForecastWeatherBinding
import com.avtograv.weatherapp.model.DataWeather


class AdapterRecyclerView : ListAdapter<DataWeather, RecyclerView.ViewHolder>(
    AsyncDifferConfig.Builder(DiffCallback()).build()
) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return when (position) {
            TYPE_NOW_WEATHER -> {
                val binding = ItemCurrentWeatherBinding.inflate(
                    LayoutInflater.from(viewGroup.context), viewGroup, false
                )
                CurrentWeatherHolder(binding)
            }
            TYPE_WEATHER_THREE_DAYS -> {
                val binding = ItemForecastWeatherBinding.inflate(
                    LayoutInflater.from(viewGroup.context), viewGroup, false
                )
                ForecastWeatherHolder(binding)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is CurrentWeatherHolder -> holder.bind(item)
            is ForecastWeatherHolder -> holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_NOW_WEATHER
            1 -> TYPE_WEATHER_THREE_DAYS
            else -> throw IllegalArgumentException()
        }
    }


    private class DiffCallback : DiffUtil.ItemCallback<DataWeather>() {
        override fun areItemsTheSame(oldItem: DataWeather, newItem: DataWeather) =
            oldItem.currentWeather.id == newItem.currentWeather.id

        override fun areContentsTheSame(oldItem: DataWeather, newItem: DataWeather) =
            oldItem == newItem
    }


    inner class CurrentWeatherHolder(private val itemBinding: ItemCurrentWeatherBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: DataWeather) {
            itemBinding.tvTempNow.text = item.currentWeather.tempNow
            itemBinding.tvAboutWeatherNow.text = item.currentWeather.aboutWeatherNow
            "AQI  ${item.currentWeather.feelsLike}".also { itemBinding.button.text = it }
        }
    }

    inner class ForecastWeatherHolder(private val itemBinding: ItemForecastWeatherBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: DataWeather) {
            itemBinding.tvAboutWeatherToday.text = item.weatherToday.text_weather
            itemBinding.rvTodayTempMax.text = item.weatherToday.max_temp
            itemBinding.rvTodayTempMin.text = item.weatherToday.min_temp

            itemBinding.tvAboutWeatherTomorrow.text = item.weatherTomorrow.text_weather
            itemBinding.rvTomorrowTempMax.text = item.weatherTomorrow.max_temp
            itemBinding.rvTomorrowTempMin.text = item.weatherTomorrow.min_temp

            itemBinding.tvTextAfterTomorrow.text = item.weatherAfterTomorrow.dayAfterTomorrow
            itemBinding.tvAboutWeatherAfterTomorrow.text = item.weatherAfterTomorrow.text_weather
            itemBinding.rvAfterTomorrowTempMax.text = item.weatherAfterTomorrow.max_temp
            itemBinding.rvAfterTomorrowTempMin.text = item.weatherAfterTomorrow.min_temp
        }
    }

    companion object {
        const val TYPE_NOW_WEATHER = 0
        const val TYPE_WEATHER_THREE_DAYS = 1
    }
}