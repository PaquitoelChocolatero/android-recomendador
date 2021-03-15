package es.uc3m.g1.musey.model.database.search

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(search: Search)

    @Transaction
    @Query("SELECT * FROM Search")
    fun findAll(): LiveData<List<SearchWithTracks>>
}