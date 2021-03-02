package es.uc3m.g1.musey.model

import android.content.Context
import androidx.lifecycle.LiveData
import es.uc3m.g1.musey.model.api.LastFM
import es.uc3m.g1.musey.model.api.lastfm.Track
import es.uc3m.g1.musey.model.database.AppDatabase
import es.uc3m.g1.musey.model.database.search.Search
import es.uc3m.g1.musey.model.database.search.SearchDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository (context: Context) {
    val LASTFM_URL = "https://ws.audioscrobbler.com/2.0/"

    private val database: AppDatabase = AppDatabase.getDatabase(context)
    private val searches: SearchDao = database.getSearchDao()
    private val lastfm: LastFM = Retrofit.Builder()
        .baseUrl(LASTFM_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LastFM::class.java)

    fun getRecentSearches(): LiveData<List<Search>> {
        return searches.findAll()
    }
    suspend fun addRecentSearch(search: Search) {
        searches.insertSearch(search)
    }

    fun getSimilar(track: Track): List<Track> {
        // TODO mal: hacer asíncrono
        val call = lastfm.getSimilar(track.artist.name, track.title)
        val resp = call.execute()

        var response: List<Track> = emptyList()

        // TODO mal: null safety
        if (resp.isSuccessful) {
            response = resp?.body()?.similarTracks?.track ?: response
        }

        return response
    }
}