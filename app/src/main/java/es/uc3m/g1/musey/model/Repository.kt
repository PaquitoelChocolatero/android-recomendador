package es.uc3m.g1.musey.model

import android.content.Context
import androidx.lifecycle.LiveData
import es.uc3m.g1.musey.model.api.RetrofitInstance
import es.uc3m.g1.musey.model.api.lastfm.Track
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

    fun getSimilar(track: Track): List<Track> {
        // TODO mal: hacer asíncrono
        val call = RetrofitInstance.lastfm.getSimilar(track.artist.name, track.title)

        val resp = call.execute()

        var response: List<Track> = emptyList()

        // TODO mal: null safety
        if (resp.isSuccessful) {
            response = resp?.body()?.similarTracks?.track ?: response
        }

        return response
    }

    fun getTrack(track: String): List<Track> {
        // TODO mal: hacer asíncrono
        val call = RetrofitInstance.lastfm.search(track)

        val resp = call.execute()

        var response: List<Track> = emptyList()

        // TODO mal: null safety
        if (resp.isSuccessful) {
            response = resp?.body()?.tracks ?: response
        }

        return response
    }
}