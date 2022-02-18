package com.avtograv.weatherapp.data.location.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.*

@Dao
interface LocationDao {

    @Query("SELECT * FROM my_location_table ORDER BY date DESC")
    fun getLocations(): LiveData<List<LocationEntity>>

    @Query("SELECT * FROM my_location_table WHERE id=(:id)")
    fun getLocation(id: UUID): LiveData<LocationEntity>

    @Update
    fun updateLocation(locationEntity: LocationEntity)

    @Insert
    fun addLocation(locationEntity: LocationEntity)

    @Insert
    fun addLocations(locationEntities: List<LocationEntity>)
}