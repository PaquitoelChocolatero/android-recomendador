package es.uc3m.g1.musey.model.database.recommendation

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecommendationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(track: Recommendation)

    @Query("SELECT * FROM Recommendation")
    fun findAll(): LiveData<List<Recommendation>>
}