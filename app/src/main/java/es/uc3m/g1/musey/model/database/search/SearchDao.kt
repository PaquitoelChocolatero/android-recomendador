package es.uc3m.g1.musey.model.database.search

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(search: Search)
    @Query("SELECT * FROM Search")
    fun findAll(): LiveData<List<Search>>
}