package es.uc3m.g1.musey.model.database.track

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TrackDBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(track: TrackDB)

    @Query("SELECT * FROM TrackDB")
    fun findAll(): LiveData<List<TrackDB>>
}