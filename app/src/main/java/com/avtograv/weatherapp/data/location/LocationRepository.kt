package com.avtograv.weatherapp.data.location

import android.content.Context
import com.avtograv.weatherapp.data.location.db.MyLocationDatabase
import com.avtograv.weatherapp.data.location.db.MyLocationEntity
import java.util.concurrent.ExecutorService

class LocationRepository private constructor(
    private val myLocationDatabase: MyLocationDatabase,
    private val myLocationManager: MyLocationManager,
    private val executor: ExecutorService,
) {

    // Database related fields/methods:
    private val locationDao = myLocationDatabase.locationDao()

    /**
     * Adds list of locations to the database.
     */
    fun addLocations(myLocationEntities: List<MyLocationEntity>) {
        executor.execute {
            locationDao.addLocations(myLocationEntities)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: LocationRepository? = null

        fun getInstance(context: Context, executor: ExecutorService): LocationRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocationRepository(
                    MyLocationDatabase.getInstance(context),
                    MyLocationManager.getInstance(context),
                    executor)
                    .also { INSTANCE = it }
            }
        }
    }
}