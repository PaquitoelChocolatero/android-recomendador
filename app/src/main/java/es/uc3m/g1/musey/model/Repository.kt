package es.uc3m.g1.musey.model

import android.content.Context
import androidx.lifecycle.LiveData
import es.uc3m.g1.musey.model.api.LastFM
import es.uc3m.g1.musey.model.api.lastfm.Track
import es.uc3m.g1.musey.model.database.AppDatabase
import es.uc3m.g1.musey.model.database.search.Search
import es.uc3m.g1.musey.model.database.search.SearchDao
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository (context: Context) {
    val LASTFM_URL = "https://ws.audioscrobbler.com/2.0/"

    private val database: AppDatabase = AppDatabase.getDatabase(context)
    private val searches: SearchDao = database.getSearchDao()
    private val lastfm: LastFM by lazy {
    /*
        val httpBuilder = OkHttpClient.Builder()

        val certificatePinner = CertificatePinner.Builder()
            .add("ws.audioscrobbler.com", "sha256/Py8r09gmJxFrsGI4ef5Y+1C+xCqtPwUcOB9blDfvThw=")
            .build()

        val okHttpClient = OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .build()
    */
        Retrofit.Builder()
            .baseUrl(LASTFM_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.client(okHttpClient)
            .build()
            .create(LastFM::class.java)
    }

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
        /*try {
            val resp = call.execute()
        }catch (e: javax.net.ssl.SSLHandshakeException){
            resp.code =
        }*/

        var response: List<Track> = emptyList()

        // TODO mal: null safety
        if (resp.isSuccessful) {
            response = resp?.body()?.similarTracks?.track ?: response
        }

        return response
    }
}