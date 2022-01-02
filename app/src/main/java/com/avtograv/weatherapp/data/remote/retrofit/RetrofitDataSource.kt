package com.avtograv.weatherapp.data.remote.retrofit

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import com.avtograv.weatherapp.data.remote.RemoteDataSource
import com.avtograv.weatherapp.model.DataCurrentWeather
import com.avtograv.weatherapp.model.DataForecastWeather
import com.avtograv.weatherapp.model.DataLatLon
import com.avtograv.weatherapp.model.DataWeather
import java.util.*


internal class RetrofitDataSource(private val api: ApiService) : RemoteDataSource {

    @RequiresApi(Build.VERSION_CODES.N)
    fun getDateTime(unixSeconds: Int): String {
        val date = Date(unixSeconds * 1000L)
        val simpleDateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        return simpleDateFormat.format(date)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun loadingDailyForecast(latLocation: String, lonLocation: String):
            List<DataWeather> {
        val details = api.loadForecastWeather(latLocation, lonLocation)
        return listOf(
            DataWeather(
                DataCurrentWeather(
                    0,
                    "",
                    details.current.temp?.toInt().toString(),
                    details.current.weather[0].main,
                    details.current.feelsLike.toInt().toString()
                ),
                DataForecastWeather(
                    "", "", "", "", ""
                ),
                DataForecastWeather(
                    "", "", "", "", ""
                ),
                DataForecastWeather(
                    "", "", "", "", ""
                )
            ),

            DataWeather(
                DataCurrentWeather(
                    1,
                    "", "", "", ""
                ),
                DataForecastWeather(
                    "",
                    details.daily[0].weather[0].main,
                    details.daily[0].temp.min.toInt().toString(),
                    details.daily[0].temp.max.toInt().toString(),
                    ""
                ),
                DataForecastWeather(
                    "",
                    details.daily[1].weather[0].main,
                    details.daily[1].temp.min.toInt().toString(),
                    details.daily[1].temp.max.toInt().toString(),
                    ""
                ),
                DataForecastWeather(
                    "",
                    details.daily[2].weather[0].main,
                    details.daily[2].temp.min.toInt().toString(),
                    details.daily[2].temp.max.toInt().toString(),
                    getDateTime(details.daily[2].time) + " - "
                )
            )
        )
    }

    override suspend fun getCoordinates(nameLocation: String): List<DataLatLon> {
        val details = api.loadCoordinatesByLocation(nameLocation)
        return listOf(
            DataLatLon(
                details[0].name,
                details[0].lat.toString(),
                details[0].lon.toString()
            )
        )
    }
}