package es.uc3m.g1.musey.model

import android.content.Context
import androidx.lifecycle.LiveData
import es.uc3m.g1.musey.model.database.AppDatabase
import es.uc3m.g1.musey.model.database.search.Search
import es.uc3m.g1.musey.model.database.search.SearchDao

class Repository (context: Context) {
    private val database: AppDatabase = AppDatabase.getDatabase(context)
    private val searches: SearchDao = database.getSearchDao()

    fun getRecentSearches(): LiveData<List<Search>> {
        return searches.findAll()
    }
    suspend fun addRecentSearch(search: Search) {
        searches.insertSearch(search)
    }
}