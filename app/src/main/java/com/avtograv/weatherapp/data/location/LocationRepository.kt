package com.avtograv.weatherapp.data.location

import android.content.Context
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.avtograv.weatherapp.data.location.db.LocationDatabase
import com.avtograv.weatherapp.data.location.db.LocationEntity
import java.util.*
import java.util.concurrent.ExecutorService

private const val TAG = "LocationRepository"

class LocationRepository private constructor(
    LocationDatabase: LocationDatabase,
    private val managerLocation: ManagerLocation,
    private val executor: ExecutorService
) {

    private val locationDao = LocationDatabase.locationDao()

    fun getLocations(): LiveData<List<LocationEntity>> = locationDao.getLocations()

    fun getLocation(id: UUID): LiveData<LocationEntity> = locationDao.getLocation(id)

    fun updateLocation(myLocationEntity: LocationEntity) {
        executor.execute {
            locationDao.updateLocation(myLocationEntity)
        }
    }

    fun addLocation(myLocationEntity: LocationEntity) {
        executor.execute {
            locationDao.addLocation(myLocationEntity)
        }
    }

    fun addLocations(myLocationEntities: List<LocationEntity>) {
        executor.execute {
            locationDao.addLocations(myLocationEntities)
        }
    }

    val receivingLocationUpdates: LiveData<Boolean> = managerLocation.receivingLocationUpdates

    @MainThread
    fun startLocationUpdates() = managerLocation.startLocationUpdates()

    @MainThread
    fun stopLocationUpdates() = managerLocation.stopLocationUpdates()

    companion object {
        @Volatile private var INSTANCE: LocationRepository? = null

        fun getInstance(context: Context, executor: ExecutorService): LocationRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocationRepository(
                    LocationDatabase.getInstance(context),
                    ManagerLocation.getInstance(context),
                    executor)
                    .also { INSTANCE = it }
            }
        }
    }
}